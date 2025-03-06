package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;

import java.util.List;

public interface CartegoryServiceInterface {
    List<Category> getCategories();
    void addCategory(Category category);
    String removeCategory(Long id);
    String updateCategory(Category category, Long id);
}
