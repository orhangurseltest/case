package com.getir.readingisgood.exception;

import com.getir.readingisgood.model.Customer;

public class CustomerNotFoundException extends BaseRuntimeException {

    public CustomerNotFoundException(String email) {
        super(formatMessage(email));
    }

    private static String formatMessage(String email) {
        return String.format("Customer email not found: %s", email);
    }
}
