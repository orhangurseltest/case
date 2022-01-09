package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.customer.AddCustomerDto;
import com.getir.readingisgood.exception.CustomerAlreadyExistException;
import com.getir.readingisgood.model.Customer;

public interface CustomerService {

    Customer addCustomer(AddCustomerDto addCustomerDto) throws CustomerAlreadyExistException;

    Customer findCustomerByMail(String mail);

    Customer findCustomerByCustomerId(String customerId);

}
