package com.getir.readingisgood.dto.book;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UpdateBookDto {

    @NotEmpty(message = "Book name may not be empty")
    private String bookName;

    @Min(value = 1, message = "Stock should not be less than 1")
    private Integer stock;
}
