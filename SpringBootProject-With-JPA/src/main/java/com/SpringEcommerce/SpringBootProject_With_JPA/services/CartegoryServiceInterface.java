package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.payload.CategoryDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.PaginationResponse;

import java.util.List;

public interface CartegoryServiceInterface {
    PaginationResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    CategoryDTO removeCategory(Long id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);
}
