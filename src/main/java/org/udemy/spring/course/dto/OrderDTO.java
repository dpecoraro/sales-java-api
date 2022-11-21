package org.udemy.spring.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.udemy.spring.course.validations.NotEmptyListValidation;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @NotNull(message = "{field.clientId.mandatory}")
    private Integer clientId;
    @NotNull(message = "{field.total.mandatory}")
    private BigDecimal total;
    @NotEmptyListValidation
    private List<ProductOrderDTO> itens;
}
