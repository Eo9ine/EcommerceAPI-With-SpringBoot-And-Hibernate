package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.CategoryResponse;

import java.util.List;

public interface CartegoryServiceInterface {
    CategoryResponse getCategories();
    void addCategory(Category category);
    String removeCategory(Long id);
    Category updateCategory(Category category, Long id);
}
