package com.SpringEcommerce.SpringBootProject_With_JPA.controllers;

import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.ProductResponse;
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
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize,
            @RequestParam String sortBy,
            @RequestParam String sortOrder
    )
    {
        ProductResponse products = productServiceImp.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
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
