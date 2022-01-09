package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.book.AddBookDto;
import com.getir.readingisgood.dto.book.BookDto;
import com.getir.readingisgood.dto.book.UpdateBookDto;
import com.getir.readingisgood.exception.BookAlreadyExistException;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody AddBookDto addBookDto) throws BookAlreadyExistException {

        Book book = bookService.addBook(addBookDto);
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book,bookDto);

        return ResponseEntity.ok(bookDto);
    }

    @PutMapping("/update")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody UpdateBookDto updateBookDto) {

        Book book = bookService.updateBook(updateBookDto);
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book,bookDto);

        return ResponseEntity.ok(bookDto);
    }
}
