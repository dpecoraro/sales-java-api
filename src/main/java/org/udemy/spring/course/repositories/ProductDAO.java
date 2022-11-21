package org.udemy.spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.udemy.spring.course.domain.bo.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
