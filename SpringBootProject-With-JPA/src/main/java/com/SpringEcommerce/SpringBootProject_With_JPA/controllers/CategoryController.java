package com.SpringEcommerce.SpringBootProject_With_JPA.controllers;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;
import com.SpringEcommerce.SpringBootProject_With_JPA.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories()
    {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<String> addCategory(@Valid @RequestBody Category category)
    {
        categoryService.addCategory(category);
        return ResponseEntity.ok("Successfully added the category");
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId,@RequestBody Category category)
    {

            categoryService.updateCategory(category, categoryId);
            return ResponseEntity.ok("id: " + categoryId + " is successfully updated");

    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId)
    {

            categoryService.removeCategory(categoryId);
            return ResponseEntity.ok("id: " + categoryId + "is successfully updated");

    }


}
