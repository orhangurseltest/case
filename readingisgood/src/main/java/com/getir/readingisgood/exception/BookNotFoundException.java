package com.getir.readingisgood.exception;

public class BookNotFoundException extends BaseRuntimeException{
    public BookNotFoundException(String bookName) {
        super(bookName);
    }
    private static String formatMessage(String bookName) {
        return String.format("Book not found: %s", bookName);
    }

}
