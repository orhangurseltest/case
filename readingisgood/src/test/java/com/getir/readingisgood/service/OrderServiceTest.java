package com.getir.readingisgood.service;

import com.getir.readingisgood.AbstractTest;
import com.getir.readingisgood.dto.book.AddBookDto;
import com.getir.readingisgood.dto.customer.AddCustomerDto;
import com.getir.readingisgood.dto.order.AddOrderDto;
import com.getir.readingisgood.enums.OrderState;
import com.getir.readingisgood.exception.BookAlreadyExistException;
import com.getir.readingisgood.exception.CustomerAlreadyExistException;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.util.BookUtil;
import com.getir.readingisgood.util.CustomerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class OrderServiceTest extends AbstractTest {

    @Autowired
    BookService bookService;

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    Book book ;
    Customer customer;

    @Test
    @org.junit.jupiter.api.Order(1)
    public void addOrder() throws BookAlreadyExistException, CustomerAlreadyExistException {
        AddBookDto addBookDto = BookUtil.getAddBookDtoForOrder();
        AddCustomerDto addCustomerDto = CustomerUtil.getAddCustomerDtoForOrder();

        book = bookService.addBook(addBookDto);

        customer = customerService.addCustomer(addCustomerDto);

        AddOrderDto addOrderDto = new AddOrderDto();
        addOrderDto.setCount(300);
        addOrderDto.setCustomerId(customer.getCustomerId());
        addOrderDto.setBookId(book.getBookId());

        Order order = orderService.addOrder(addOrderDto);

        Assertions.assertNotNull(order);
        Assertions.assertEquals(OrderState.REJECTED,order.getOrderState());
        Assertions.assertEquals(300,order.getCount());

        addOrderDto.setCount(1);

        Order order2 = orderService.addOrder(addOrderDto);

        Assertions.assertNotNull(order2);
        Assertions.assertEquals(OrderState.APPROVED,order2.getOrderState());
        Assertions.assertEquals(1,order2.getCount());
        Assertions.assertEquals(BigDecimal.ONE,order2.getAmount());
    }

}
