package com.SpringEcommerce.SpringBootProject_With_JPA.controllers;

import com.SpringEcommerce.SpringBootProject_With_JPA.config.AppConstant;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.PaginationResponse;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductServiceImp productServiceImp;

    @PostMapping("/admin/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(
            @PathVariable Long categoryId,
            @RequestBody ProductDTO productDTO
            )
    {
        ProductDTO saveProductDTO = productServiceImp.addProduct(categoryId,productDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<PaginationResponse> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstant.SORT_PRODUCT_BY, required = false) String sortBy,
            @RequestParam(name = "sortOder", defaultValue = AppConstant.SORT_DIRECTION, required = false) String sortOrder
    )
    {
        PaginationResponse products = productServiceImp.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> productDTOResponseEntity(@PathVariable Long productId)
    {
        ProductDTO product = productServiceImp.deleteProduct(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }




    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<PaginationResponse<ProductDTO>> getProductByCategory(
            @PathVariable Long categoryId,
            @RequestParam(name = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize
    )
    {
        PaginationResponse<ProductDTO> productResponse = productServiceImp.getProductByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<PaginationResponse<ProductDTO>> getProductByKeyword(
            @PathVariable String keyword)
    {
        PaginationResponse<ProductDTO> productResponse = productServiceImp.getProductByKeyword(keyword);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);

    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody  ProductDTO productDTO)
    {
        ProductDTO savedProductDTO = productServiceImp.updateProduct(productId, productDTO);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/admin/product/{productId}/images")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long productId, @RequestParam MultipartFile image) throws IOException {
        ProductDTO savedProductDTO = productServiceImp.updateProductImage(productId, image);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.ACCEPTED);
    }
}
