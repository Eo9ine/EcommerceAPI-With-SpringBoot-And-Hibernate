package com.SpringEcommerce.SpringBootProject_With_JPA.controllers;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Product;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.CategoryDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductResponse;
import com.SpringEcommerce.SpringBootProject_With_JPA.services.CategoryService;
import com.SpringEcommerce.SpringBootProject_With_JPA.services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductServiceImp productServiceImp;

    @PostMapping("/admin/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(
            @PathVariable Long categoryId,
            @RequestBody Product product
            )
    {
        ProductDTO productDTO = productServiceImp.addProduct(categoryId,product);
        return new ResponseEntity<>(productDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/public/product")
    public ResponseEntity<ProductResponse> getAllProducts()
    {
        ProductResponse products = productServiceImp.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/admin/product/{productId}")
    public ResponseEntity<ProductDTO> productDTOResponseEntity(@PathVariable Long productId)
    {
        ProductDTO product = productServiceImp.deleteProduct(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

}
