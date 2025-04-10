package com.SpringEcommerce.SpringBootProject_With_JPA.repository;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDBRepository extends JpaRepository<Product, Long> {
}
