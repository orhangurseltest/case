package com.getir.readingisgood.dto.book;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AddBookDto {

    @NotEmpty(message="Book name may not be empty")
    private String bookName;
    @NotEmpty(message="Author name may not be empty")
    private String authorName;

    @Min(value = 1, message = "Stock should not be less than 1")
    private Integer stock;

    @Min(value = 1, message = "Amount should not be less than 1")
    private BigDecimal amount;
}
