package org.udemy.spring.course.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.udemy.spring.course.domain.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderStore")
public class OrderStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "order_date")
    private LocalDate date;

    @Column(name = "total", length = 20, precision = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "orderStore")
    private List<ProductOrder> orderStore;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private OrderStatus status;
}
