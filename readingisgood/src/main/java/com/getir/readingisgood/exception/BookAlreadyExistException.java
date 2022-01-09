package com.getir.readingisgood.exception;

import com.getir.readingisgood.model.Book;

public class BookAlreadyExistException extends BaseException{
    public BookAlreadyExistException(Book book) {
        super(formatMessage(book));
    }

    private static String formatMessage(Book book){
        return String.format("%s book already exist.",book.getBookName());
    }


}