package org.udemy.spring.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductInfoDTO {
    private String productDescription;
    private BigDecimal unitPrice;
    private Integer quantity;
}
