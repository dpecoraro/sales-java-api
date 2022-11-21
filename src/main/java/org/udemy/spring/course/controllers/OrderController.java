package org.udemy.spring.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.udemy.spring.course.domain.bo.OrderStore;
import org.udemy.spring.course.domain.bo.ProductOrder;
import org.udemy.spring.course.domain.enums.OrderStatus;
import org.udemy.spring.course.dto.OrderDTO;
import org.udemy.spring.course.dto.OrderInfoDTO;
import org.udemy.spring.course.dto.OrderProductInfoDTO;
import org.udemy.spring.course.dto.UpdateOrderStatusDTO;
import org.udemy.spring.course.services.OrderService;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer post(@RequestBody
                        @Valid OrderDTO dto) {
        OrderStore orderStore = service.save(dto);
        return orderStore.getId();
    }

    @GetMapping("{id}")
    public OrderInfoDTO get(@PathVariable Integer id) {
        return service.getOrderCompleted(id).map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("orderId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer orderId, @RequestBody UpdateOrderStatusDTO dto) {
        String status = dto.getStatus();
        service.updateStatus(orderId, OrderStatus.valueOf(status));
    }

    private OrderInfoDTO converter(OrderStore order) {
        return OrderInfoDTO.builder()
                .code(order.getId())
                .date(order.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getClient().getCpf())
                .clientName(order.getClient().getName())
                .total(order.getTotal())
                .status(order.getStatus().name())
                .orderProductInfoList(converter(order.getOrderStore()))
                .build();
    }

    private List<OrderProductInfoDTO> converter(List<ProductOrder> itens) {
        if (CollectionUtils.isEmpty(itens))
            return Collections.EMPTY_LIST;

        return itens.stream().map(i ->
                OrderProductInfoDTO.builder()
                        .productDescription(i.getProduct().getDescription())
                        .quantity(i.getQuantity())
                        .unitPrice(i.getProduct().getPrice())
                        .build()
        ).collect(Collectors.toList());
    }
}
