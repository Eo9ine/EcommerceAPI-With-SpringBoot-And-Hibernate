package com.SpringEcommerce.SpringBootProject_With_JPA.repository;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
