package com.getir.readingisgood.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String bookId;
    private String bookName;
    private String authorName;
    private BigDecimal amount;
    private Integer stock;
    private String createdBy;
    private LocalDate createdDate;
}
