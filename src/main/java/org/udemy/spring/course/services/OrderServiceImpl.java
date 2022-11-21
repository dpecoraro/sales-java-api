package org.udemy.spring.course.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.udemy.spring.course.domain.bo.Client;
import org.udemy.spring.course.domain.bo.OrderStore;
import org.udemy.spring.course.domain.bo.Product;
import org.udemy.spring.course.domain.bo.ProductOrder;
import org.udemy.spring.course.domain.enums.OrderStatus;
import org.udemy.spring.course.dto.OrderDTO;
import org.udemy.spring.course.dto.ProductOrderDTO;
import org.udemy.spring.course.exceptions.OrderException;
import org.udemy.spring.course.exceptions.OrderNotFoundException;
import org.udemy.spring.course.repositories.ClientDAO;
import org.udemy.spring.course.repositories.OrderDAO;
import org.udemy.spring.course.repositories.ProductDAO;
import org.udemy.spring.course.repositories.ProductOrderDAO;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderDAO dao;
    private final ClientDAO clientDAO;
    private final ProductDAO productDAO;
    private final ProductOrderDAO productOrderDAO;

    @Override
    public OrderStore save(OrderDTO dto) {
        OrderStore order = new OrderStore();
        Client client = clientDAO
                .findById(dto.getClientId()).orElseThrow(() -> new OrderException("Client not found"));
        order.setTotal(dto.getTotal());
        order.setDate(LocalDate.now());
        order.setClient(client);

        List<ProductOrder> productItens = convertItens(order, dto.getItens());
        productOrderDAO.saveAll(productItens);
        order.setOrderStore(productItens);
        return order;
    }

    @Override
    public Optional<OrderStore> getOrderCompleted(Integer id) {
        return dao.findByIdFetchProduct(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, OrderStatus status) {
        dao.findById(id).map(order -> {
            order.setStatus(status);
            return dao.save(order);
        }).orElseThrow(() -> new OrderNotFoundException());
    }

    private List<ProductOrder> convertItens(OrderStore order, List<ProductOrderDTO> itens) {
        if (itens.isEmpty())
            throw new OrderException("Empty list itens, please fill it");

        return itens.stream().map(dto -> {
            Product product = productDAO.findById(dto.getProductId())
                    .orElseThrow(() -> new OrderException("Product not in the list, please ensure your are passing a correct one"));

            ProductOrder productOrder = new ProductOrder();
            productOrder.setQuantity(dto.getQuantity());
            productOrder.setOrderStore(order);
            productOrder.setProduct(product);
            return productOrder;
        }).collect(Collectors.toList());
    }
}
