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

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts()
    {
        ProductResponse products = productServiceImp.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> productDTOResponseEntity(@PathVariable Long productId)
    {
        ProductDTO product = productServiceImp.deleteProduct(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }


    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable Long categoryId)
    {
        ProductResponse productResponse = productServiceImp.getProductByCategory(categoryId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyword(@PathVariable String keyword)
    {
        ProductResponse productResponse = productServiceImp.getProductByKeyword(keyword);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);

    }

    @PutMapping("/admin/product/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody  ProductDTO productDTO)
    {
        ProductDTO savedProductDTO = productServiceImp.updateProduct(productId, productDTO);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.ACCEPTED);
    }

}
