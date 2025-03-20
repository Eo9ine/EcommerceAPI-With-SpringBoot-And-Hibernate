package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.CategoryDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.CategoryResponse;

import java.util.List;

public interface CartegoryServiceInterface {
    CategoryResponse getCategories();
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    CategoryDTO removeCategory(Long id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);
}
