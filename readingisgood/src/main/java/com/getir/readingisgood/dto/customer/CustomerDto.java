package com.getir.readingisgood.dto.customer;

import com.getir.readingisgood.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String createdBy;
    private LocalDate createdDate;

}
