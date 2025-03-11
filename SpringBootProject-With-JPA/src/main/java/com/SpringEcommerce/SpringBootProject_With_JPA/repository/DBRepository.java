package com.SpringEcommerce.SpringBootProject_With_JPA.repository;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);
}
