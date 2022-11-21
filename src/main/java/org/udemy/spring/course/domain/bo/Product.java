package org.udemy.spring.course.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "Description")
    @NotEmpty(message = "{field.productDescription.notEmpty}")
    private String description;

    @Column(name = "unit_price")
    @NotNull(message = "{field.productPrice.notEmpty}")
    private BigDecimal price;
}
