package org.udemy.spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.udemy.spring.course.domain.bo.ProductOrder;

public interface ProductOrderDAO extends JpaRepository<ProductOrder, Integer> {
}
