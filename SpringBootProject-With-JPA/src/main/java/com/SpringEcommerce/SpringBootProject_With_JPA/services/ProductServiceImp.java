package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.exceptions.CustomResourceNotFoundException;
import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;
import com.SpringEcommerce.SpringBootProject_With_JPA.model.Product;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.CategoryDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductResponse;
import com.SpringEcommerce.SpringBootProject_With_JPA.repository.DBRepository;
import com.SpringEcommerce.SpringBootProject_With_JPA.repository.ProductDBRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductServiceInterface{

    @Autowired
    private DBRepository categoryRepository;
    @Autowired
    private ProductDBRepository productDBRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Category","categoryId", categoryId));


        double specialPrice =  product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
        product.setCategory(category);
        product.setSpecialPrice(specialPrice);
        product.setProductImage("default.png");
        Product saveProduct = productDBRepository.save(product);
        return modelMapper.map(saveProduct, ProductDTO.class);

    }

    @Override
    public ProductResponse getAllProducts() {

        List<Product> fetchingProducts = productDBRepository.findAll();

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

}
