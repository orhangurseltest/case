package com.getir.readingisgood.service;

import com.getir.readingisgood.AbstractTest;
import com.getir.readingisgood.dto.book.AddBookDto;
import com.getir.readingisgood.dto.book.UpdateBookDto;
import com.getir.readingisgood.exception.BookAlreadyExistException;
import com.getir.readingisgood.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class BookServiceTest extends AbstractTest {

    @Autowired
    private BookService bookService;

    private final String bookName = "Kitap1";
    private String bookId;

    @Test
    @Order(1)
    public void addBook() throws BookAlreadyExistException {
        AddBookDto addBookDto = new AddBookDto();
        addBookDto.setBookName(bookName);
        addBookDto.setStock(10);
        addBookDto.setAuthorName("Yazar");
        addBookDto.setAmount(BigDecimal.valueOf(30L));

        Book book = bookService.addBook(addBookDto);
        bookId = book.getBookId();

        Assertions.assertNotNull(book);
        Assertions.assertEquals(10,book.getStock());
        Assertions.assertEquals(bookName,book.getBookName());
        Assertions.assertEquals("Yazar",book.getAuthorName());
        Assertions.assertEquals(BigDecimal.valueOf(30L),book.getAmount());
        Assertions.assertNotNull(book.getBookId());
        Assertions.assertNotNull(book.getCreatedDate());

    }

    @Test
    @Order(2)
    public void updateBook(){
        UpdateBookDto updateBookDto = new UpdateBookDto();
        updateBookDto.setBookName(bookName);
        updateBookDto.setStock(20);

        Book book = bookService.updateBook(updateBookDto);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(20,book.getStock());
        Assertions.assertEquals(bookName,book.getBookName());
        Assertions.assertEquals("Yazar",book.getAuthorName());
        Assertions.assertEquals(BigDecimal.valueOf(30L),book.getAmount());
        Assertions.assertNotNull(book.getBookId());
        Assertions.assertNotNull(book.getCreatedDate());
    }

    @Test
    @Order(3)
    public void findBookByBookName(){
        Book book = bookService.findBookByBookName(bookName);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(bookId,book.getBookId());
        Assertions.assertEquals(20,book.getStock());
        Assertions.assertEquals(bookName,book.getBookName());
        Assertions.assertEquals("Yazar",book.getAuthorName());
        Assertions.assertEquals(BigDecimal.valueOf(30L),book.getAmount());
        Assertions.assertNotNull(book.getBookId());
        Assertions.assertNotNull(book.getCreatedDate());

    }

    @Test
    @Order(4)
    public void findBookByBookId(){
        Book book = bookService.findBookByBookId(bookId);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(bookId,book.getBookId());
        Assertions.assertEquals(20,book.getStock());
        Assertions.assertEquals(bookName,book.getBookName());
        Assertions.assertEquals("Yazar",book.getAuthorName());
        Assertions.assertEquals(BigDecimal.valueOf(30L),book.getAmount());
        Assertions.assertNotNull(book.getBookId());
        Assertions.assertNotNull(book.getCreatedDate());

    }






}
