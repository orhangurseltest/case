package com.getir.readingisgood.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String orderId;

    private String customerId;
    private String bookId;
    private Integer count;
    private BigDecimal amount;
    private String createdBy;
    private LocalDate createdDate;

}
