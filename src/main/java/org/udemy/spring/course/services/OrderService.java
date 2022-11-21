package org.udemy.spring.course.services;

import org.udemy.spring.course.domain.bo.OrderStore;
import org.udemy.spring.course.domain.enums.OrderStatus;
import org.udemy.spring.course.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {
    OrderStore save(OrderDTO dto);

    Optional<OrderStore> getOrderCompleted(Integer id);

    void updateStatus(Integer id, OrderStatus status);
}
