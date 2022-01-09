package com.getir.readingisgood.exception;

public class BaseRuntimeException extends RuntimeException {

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String message, Exception ex) {
        super(message, ex);
    }
}
