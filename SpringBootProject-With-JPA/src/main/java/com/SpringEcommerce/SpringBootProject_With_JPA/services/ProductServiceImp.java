package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.exceptions.APIException;
import com.SpringEcommerce.SpringBootProject_With_JPA.exceptions.CustomResourceNotFoundException;
import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;
import com.SpringEcommerce.SpringBootProject_With_JPA.model.Product;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.APIResponse;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductResponse;
import com.SpringEcommerce.SpringBootProject_With_JPA.repository.DBRepository;
import com.SpringEcommerce.SpringBootProject_With_JPA.repository.ProductDBRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImp implements ProductServiceInterface{

    @Autowired
    private DBRepository categoryRepository;
    @Autowired
    private ProductDBRepository productDBRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Category","categoryId", categoryId));



        Product product = modelMapper.map(productDTO, Product.class);

        Product existingProduct = productDBRepository.findByProductName(product.getProductName());

        if(existingProduct != null)
            throw new APIException("Product already exists.");

        double specialPrice =  product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
        product.setCategory(category);
        product.setSpecialPrice(specialPrice);
        product.setImage("default.png");
        Product saveProduct = productDBRepository.save(product);
        return modelMapper.map(saveProduct, ProductDTO.class);

    }

    @Override
    public ProductResponse getAllProducts() {

        List<Product> fetchingProducts = productDBRepository.findAll();

        if (fetchingProducts.isEmpty())
        {
            throw new APIException("No products found.");
        }

        List<ProductDTO> productDTOS = fetchingProducts.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product product = productDBRepository.findById(productId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Product", "productId", productId));

        productDBRepository.delete(product);

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;

    }

    @Override
    public ProductResponse getProductByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Category", "categoryId", categoryId));


        List<Product> products = productDBRepository.findByCategoryOrderByPriceAsc(category);

        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;

    }

    @Override
    public ProductResponse getProductByKeyword(String keyword) {
        List<Product> products = productDBRepository.findByProductNameLikeIgnoreCase("%" + keyword + "%");

        if(products.isEmpty())
            throw new CustomResourceNotFoundException("Product", "productName", keyword);


        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        return new ProductResponse(productDTOS);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);

        Product productInDb =  productDBRepository.findById(productId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Product", "productId", productId));

        Product existingProduct = productDBRepository.findByProductName(product.getProductName());

        if (existingProduct != null)
            throw new APIException("Product already exists");

        productInDb.setProductName(product.getProductName());
        productInDb.setDiscount(product.getDiscount());
        productInDb.setDescription(product.getDescription());
        productInDb.setQuantity(product.getQuantity());
        productInDb.setPrice(product.getPrice());

        double specialPrice = product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
        productInDb.setSpecialPrice(specialPrice);

        Product savedProduct = productDBRepository.save(productInDb);

        return modelMapper.map(savedProduct,ProductDTO.class);

    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        Product productToUpdateImage = productDBRepository.findById(productId)
                .orElseThrow(()-> new CustomResourceNotFoundException("Product", "productId", productId));

        String path = "image/";
        String filePath = uploadImage(path,image);

        productToUpdateImage.setImage(filePath);
        productDBRepository.save(productToUpdateImage);

        return modelMapper.map(productToUpdateImage, ProductDTO.class);

    }

    private String uploadImage(String path, MultipartFile image) throws IOException {
        String fileImageName = image.getOriginalFilename();
        String randomImageFileName = UUID.randomUUID().toString();
        String newFileImageName = randomImageFileName.concat(fileImageName.substring(fileImageName.lastIndexOf('.')));


        Path directoryPath = Paths.get(path);
        if (!Files.exists(directoryPath))
            Files.createDirectories(directoryPath);

        Path filePath = directoryPath.resolve(newFileImageName);

        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return newFileImageName;
    }


}
