package com.getir.readingisgood.service;

import com.getir.readingisgood.AbstractTest;
import com.getir.readingisgood.dto.customer.AddCustomerDto;
import com.getir.readingisgood.exception.CustomerAlreadyExistException;
import com.getir.readingisgood.model.Address;
import com.getir.readingisgood.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceTest extends AbstractTest {

    @Autowired
    private CustomerService customerService;

    String email;
    String customerId;

    @Test
    @Order(1)
    public void addCustomer() throws CustomerAlreadyExistException {

        AddCustomerDto addCustomerDto = new AddCustomerDto();
        addCustomerDto.setEmail("mail@getir.com");
        addCustomerDto.setAddress(new Address("Ankara","Turkey"));
        addCustomerDto.setFirstName("Demo");
        addCustomerDto.setLastName("Demo");

        Customer customer = customerService.addCustomer(addCustomerDto);
        email = customer.getEmail();
        customerId = customer.getCustomerId();

        Assertions.assertNotNull(customer);
        Assertions.assertEquals("mail@getir.com",customer.getEmail());
        Assertions.assertEquals("Demo",customer.getFirstName());
        Assertions.assertEquals("Demo",customer.getLastName());
        Assertions.assertNotNull(customer.getCustomerId());
        Assertions.assertNotNull(customer.getCreatedDate());
    }

    @Test
    @Order(2)
    public void findCustomerByMail(){
        Customer customer = customerService.findCustomerByMail(email);

        Assertions.assertNotNull(customer);
        Assertions.assertNotNull(customer.getCustomerId());
        Assertions.assertEquals(email,customer.getEmail());
        Assertions.assertEquals("Demo",customer.getFirstName());
        Assertions.assertEquals("Demo",customer.getLastName());
        Assertions.assertNotNull(customer.getCustomerId());
        Assertions.assertNotNull(customer.getCreatedDate());

    }

    @Test
    @Order(3)
    public void findCustomerById(){
        Customer customer  =customerService.findCustomerByCustomerId(customerId);

        Assertions.assertNotNull(customer);
        Assertions.assertNotNull(customer.getCustomerId());
        Assertions.assertEquals(customerId,customer.getCustomerId());
        Assertions.assertEquals(email,customer.getEmail());
        Assertions.assertEquals("Demo",customer.getFirstName());
        Assertions.assertEquals("Demo",customer.getLastName());
        Assertions.assertNotNull(customer.getCustomerId());
        Assertions.assertNotNull(customer.getCreatedDate());
    }



}
