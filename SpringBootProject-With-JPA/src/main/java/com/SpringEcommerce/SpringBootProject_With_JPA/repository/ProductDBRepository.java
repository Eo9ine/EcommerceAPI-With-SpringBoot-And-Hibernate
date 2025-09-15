package com.SpringEcommerce.SpringBootProject_With_JPA.repository;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;
import com.SpringEcommerce.SpringBootProject_With_JPA.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDBRepository extends JpaRepository<Product, Long> {




    List<Product> findByProductNameLikeIgnoreCase(String s);

    Product findByProductName(String productName);

    Page<Product> findByCategoryOrderByPriceAsc(Category category1, Pageable category);
}
