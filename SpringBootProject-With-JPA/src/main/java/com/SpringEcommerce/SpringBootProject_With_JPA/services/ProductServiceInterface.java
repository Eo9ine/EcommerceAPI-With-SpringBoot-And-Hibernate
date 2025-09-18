package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.payload.PaginationResponse;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ProductServiceInterface {
    ProductDTO addProduct(Long categoryId, ProductDTO product);

    PaginationResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);


    ProductDTO deleteProduct(Long productId);

    PaginationResponse getProductByCategory(Long categoryId, Integer pageNumber, Integer pageSize);

    PaginationResponse getProductByKeyword(String keyword);

    ProductDTO updateProduct(Long productId, ProductDTO productDTO);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
