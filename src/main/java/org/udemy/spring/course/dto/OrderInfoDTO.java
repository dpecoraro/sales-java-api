package org.udemy.spring.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO {
    private Integer code;
    private String cpf;
    private String clientName;
    private BigDecimal total;
    private String date;
    private List<OrderProductInfoDTO> orderProductInfoList;
    private String status;

}
