package com.getir.readingisgood.util;

import com.getir.readingisgood.dto.book.AddBookDto;
import com.getir.readingisgood.dto.book.UpdateBookDto;

import java.math.BigDecimal;

public class BookUtil {

    public static AddBookDto getAddBookDto(){
        AddBookDto addBookDto = new AddBookDto();

        addBookDto.setAmount(BigDecimal.TEN);
        addBookDto.setAuthorName("Mustafa Kemal Atatürk");
        addBookDto.setBookName("Nutuk");
        addBookDto.setStock(1881);
        return addBookDto;
    }

    public static UpdateBookDto getUpdateBookDto(){
        UpdateBookDto updateBookDto = new UpdateBookDto();

        updateBookDto.setBookName("Nutuk");
        updateBookDto.setStock(1923);

        return updateBookDto;
    }

    public static AddBookDto getAddBookDtoForOrder(){
        AddBookDto addBookDto = new AddBookDto();

        addBookDto.setAmount(BigDecimal.ONE);
        addBookDto.setAuthorName("Franz Kafka");
        addBookDto.setBookName("Der Process");
        addBookDto.setStock(50);
        return addBookDto;
    }


}
