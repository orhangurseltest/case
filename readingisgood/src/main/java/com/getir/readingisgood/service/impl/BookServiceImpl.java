package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.dto.book.AddBookDto;
import com.getir.readingisgood.dto.book.UpdateBookDto;
import com.getir.readingisgood.exception.BookAlreadyExistException;
import com.getir.readingisgood.exception.BookNotFoundException;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public Book addBook(AddBookDto addBookDto) throws BookAlreadyExistException {
        Optional<Book> optionalBook = bookRepository.findBookByBookName(addBookDto.getBookName());

        if (optionalBook.isPresent())
            throw new BookAlreadyExistException(optionalBook.get());

        Book book = new Book();
        BeanUtils.copyProperties(addBookDto, book);

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book updateBook(UpdateBookDto updateBookDto) {
        Optional<Book> optionalBook = bookRepository.findBookByBookName(updateBookDto.getBookName());

        if (optionalBook.isEmpty())
            throw new BookNotFoundException(updateBookDto.getBookName());

        Book book = optionalBook.get();
        book.setStock(updateBookDto.getStock());

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findBookByBookName(String bookName) {
        return bookRepository
                .findBookByBookName(bookName)
                .orElseThrow(() -> new BookNotFoundException(bookName));
    }

    @Transactional
    @Override
    public Book findBookByBookId(String bookId) {
        return bookRepository
                .findById(bookId)
                .orElseThrow(() -> new RuntimeException(String.format("Invalid book id: %s", bookId)));
    }
}
