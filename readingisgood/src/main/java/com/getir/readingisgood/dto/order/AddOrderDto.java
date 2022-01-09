package com.getir.readingisgood.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AddOrderDto {

    @NotNull(message = "Customer id cannot be null")
    private String customerId;

    @NotNull(message = "Book id cannot be null")
    private String bookId;

    @Min(value = 1, message = "Amount should not be less than 1")
    private Integer count;
}
