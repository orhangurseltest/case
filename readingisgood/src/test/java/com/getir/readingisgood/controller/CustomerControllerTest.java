package com.getir.readingisgood.controller;

import com.getir.readingisgood.AbstractTest;
import com.getir.readingisgood.TestTokenUtils;
import com.getir.readingisgood.dto.customer.AddCustomerDto;
import com.getir.readingisgood.util.CustomerUtil;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CustomerControllerTest extends AbstractTest{

    @Autowired
    private MockMvc mockMvc;

    static AddCustomerDto addCustomerDto = CustomerUtil.getAddCustomerDto();

    @Test
    @Order(1)
    public void addCustomerFailed() throws Exception{
        String token = TestTokenUtils.getJWTToken();
        Assertions.assertNotNull(token);

        addCustomerDto.setAddress(null);
        addCustomerDto.setEmail("");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/customers/add")
                .header("Authorization", token)
                .content(asJsonString(addCustomerDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    @Order(2)
    public void addCustomerPassed() throws Exception{
        String token = TestTokenUtils.getJWTToken();
        Assertions.assertNotNull(token);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/customers/add")
                .header("Authorization", token)
                .content(asJsonString(addCustomerDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }




}
