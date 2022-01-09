package com.getir.readingisgood.controller;

import com.getir.readingisgood.AbstractTest;
import com.getir.readingisgood.dto.authentication.AuthenticationRequest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void authenticationFailed() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setPassword("getir12345");
        authenticationRequest.setUsername("getir");

        mockMvc.perform(MockMvcRequestBuilders
        .post("/authenticate/token")
        .content(asJsonString(authenticationRequest)))
        .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(2)
    public void authenticationPassed() throws Exception{
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setPassword("getir1234");
        authenticationRequest.setUsername("getir");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/authenticate/token")
                .content(asJsonString(authenticationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }


}
