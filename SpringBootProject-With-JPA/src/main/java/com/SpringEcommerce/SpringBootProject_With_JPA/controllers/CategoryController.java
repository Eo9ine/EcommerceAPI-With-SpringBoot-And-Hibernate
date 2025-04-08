package com.SpringEcommerce.SpringBootProject_With_JPA.controllers;

import com.SpringEcommerce.SpringBootProject_With_JPA.config.AppConstant;
import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.CategoryDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.CategoryResponse;
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
    public ResponseEntity<CategoryResponse> getCategories(
            @RequestParam(name = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstant.SORT_CATEGORY_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstant.SORT_CATEGORY_ORDER, required = false) String sortOrder

            )
    {
        return ResponseEntity.ok(categoryService.getCategories(pageNumber,pageSize,sortBy, sortOrder ));
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO results = categoryService.addCategory(categoryDTO);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId,@RequestBody CategoryDTO categoryDTO)
    {

            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
            return ResponseEntity.ok(savedCategoryDTO);

    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId)
    {

            categoryService.removeCategory(categoryId);
            return ResponseEntity.ok("id: " + categoryId + "is successfully updated");

    }


}
