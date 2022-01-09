package com.getir.readingisgood.exception;

import com.getir.readingisgood.model.Customer;

public class CustomerAlreadyExistException extends BaseException{
    public CustomerAlreadyExistException(Customer customer) {
        super(formatMessage(customer));
    }

    private static String formatMessage(Customer customer){
        return String.format("%s email adresss already exist.",customer.getEmail());
    }


}
