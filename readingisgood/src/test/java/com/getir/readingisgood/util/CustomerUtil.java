package com.getir.readingisgood.util;

import com.getir.readingisgood.dto.customer.AddCustomerDto;
import com.getir.readingisgood.model.Address;

public class CustomerUtil {

    public static AddCustomerDto getAddCustomerDto(){
        AddCustomerDto addCustomerDto = new AddCustomerDto();
        addCustomerDto.setFirstName("Burak");
        addCustomerDto.setLastName("Kabasakal");
        addCustomerDto.setEmail("test@getir.com");
        addCustomerDto.setAddress(new Address("Ankara","Turkey"));
        return addCustomerDto;
    }

    public static AddCustomerDto getAddCustomerDtoForOrder(){
        AddCustomerDto addCustomerDto = new AddCustomerDto();
        addCustomerDto.setFirstName("Test");
        addCustomerDto.setLastName("Test");
        addCustomerDto.setEmail("addCustomerMail@getir.com");
        addCustomerDto.setAddress(new Address("Ankara","Turkey"));
        return addCustomerDto;
    }


}
