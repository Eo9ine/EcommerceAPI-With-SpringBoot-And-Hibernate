package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Product;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductDTO;
import org.springframework.stereotype.Service;


public interface ProductServiceInterface {
    ProductDTO addProduct(Long categoryId, Product product);
}
