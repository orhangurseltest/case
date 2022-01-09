package com.getir.readingisgood.controller;

import com.getir.readingisgood.AbstractTest;
import com.getir.readingisgood.TestTokenUtils;
import com.getir.readingisgood.dto.book.AddBookDto;
import com.getir.readingisgood.dto.book.UpdateBookDto;
import com.getir.readingisgood.util.BookUtil;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    static AddBookDto addBookDto = BookUtil.getAddBookDto();
    static UpdateBookDto updateBookDto = BookUtil.getUpdateBookDto();

    @Test
    @Order(1)
    public void addBookPassed() throws Exception {
        String token = TestTokenUtils.getJWTToken();
        Assertions.assertNotNull(token);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/books/add")
                .content(asJsonString(addBookDto)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void updateBookPassed() throws Exception {
        String token = TestTokenUtils.getJWTToken();
        Assertions.assertNotNull(token);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/books/update")
                .header("Authorization", "Bearer "+token)
                .content(asJsonString(updateBookDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @Order(3)
    public void updateBookFailed() throws Exception {
        String token = TestTokenUtils.getJWTToken();
        Assertions.assertNotNull(token);

        updateBookDto.setBookName("Test");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/books/update")
                .header("Authorization", token)
                .content(asJsonString(updateBookDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }


}
