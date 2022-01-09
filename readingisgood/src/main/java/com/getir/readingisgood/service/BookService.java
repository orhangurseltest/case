package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.book.AddBookDto;
import com.getir.readingisgood.dto.book.UpdateBookDto;
import com.getir.readingisgood.exception.BookAlreadyExistException;
import com.getir.readingisgood.model.Book;

public interface BookService {

    Book addBook(AddBookDto addBookDto) throws BookAlreadyExistException;

    Book updateBook(UpdateBookDto updateBookDto);

    Book updateBook(Book book);

    Book findBookByBookName(String bookName);

    Book findBookByBookId(String bookId);
}
