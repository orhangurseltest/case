package com.getir.readingisgood.exception;

public class BaseException extends Exception {


    public BaseException(String message) {
        super(message);

    }

    public BaseException(String message, Exception exception) {
        super(message, exception);

    }
}
