package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Product;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductResponse;
import org.springframework.stereotype.Service;


public interface ProductServiceInterface {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();


    ProductDTO deleteProduct(Long productId);

    ProductResponse getProductByCategory(Long categoryId);

    ProductResponse getProductByKeyword(String keyword);

    ProductDTO updateProduct(Long productId, ProductDTO productDTO);
}
