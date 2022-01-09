package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.customer.AddCustomerDto;
import com.getir.readingisgood.dto.customer.CustomerDto;
import com.getir.readingisgood.exception.CustomerAlreadyExistException;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody AddCustomerDto addCustomerDto) throws CustomerAlreadyExistException {

        Customer customer = customerService.addCustomer(addCustomerDto);
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);

        return ResponseEntity.ok(customerDto);
    }
}
