package com.getir.readingisgood.controller;

import com.getir.readingisgood.AbstractTest;
import com.getir.readingisgood.TestTokenUtils;
import com.getir.readingisgood.dto.book.AddBookDto;
import com.getir.readingisgood.dto.customer.AddCustomerDto;
import com.getir.readingisgood.dto.order.AddOrderDto;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.service.BookService;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.util.BookUtil;
import com.getir.readingisgood.util.CustomerUtil;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;

    static String customerId;

    @Test
    @Order(1)
    public void addOrder() throws Exception {
        AddBookDto addBookDto = BookUtil.getAddBookDto();
        addBookDto.setBookName(addBookDto.getBookName()+addBookDto.getBookName());
        AddCustomerDto addCustomerDto = CustomerUtil.getAddCustomerDto();

        Book book = bookService.addBook(addBookDto);
        Customer customer = customerService.addCustomer(addCustomerDto);

        AddOrderDto addOrderDto = new AddOrderDto();
        addOrderDto.setBookId(book.getBookId());
        addOrderDto.setCustomerId(customer.getCustomerId());
        addOrderDto.setCount(5);

        customerId = customer.getCustomerId();

        String token = TestTokenUtils.getJWTToken();
        Assertions.assertNotNull(token);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/orders/add")
                .header("Authorization", token)
                .content(asJsonString(addOrderDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void findOrdersBetweenDates() throws Exception{
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(1);

        String token = TestTokenUtils.getJWTToken();
        Assertions.assertNotNull(token);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/orders/list/{customerId}",customerId)
                .param("startDate",startDate.toString())
                .param("endDate",endDate.toString())
                .header("Authorization", token))
                .andExpect(status().isOk());
    }



}
