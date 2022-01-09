package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.dto.order.AddOrderDto;
import com.getir.readingisgood.dto.statistic.StatisticDto;
import com.getir.readingisgood.enums.OrderState;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.service.BookService;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final BookService bookService;
    private final CustomerService customerService;

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Order addOrder(AddOrderDto addOrderDto) {

        Book book = bookService.findBookByBookId(addOrderDto.getBookId());
        Customer customer = customerService.findCustomerByCustomerId(addOrderDto.getCustomerId());

        Order order = new Order();
        order.setCustomerId(customer.getCustomerId());
        order.setBookId(book.getBookId());
        order.setCount(addOrderDto.getCount());
        order.setAmount(book.getAmount().multiply(BigDecimal.valueOf(addOrderDto.getCount())));

        if (book.getStock() > addOrderDto.getCount()) { // order approved book stock will be updated
            book.setStock(book.getStock() - addOrderDto.getCount());
            bookService.updateBook(book);
            order.setOrderState(OrderState.APPROVED);
        }

        return orderRepository.save(order); // order save the db with order state
    }

    @Override
    public List<Order> findAllOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {
        return orderRepository
                .findByCreatedDateBetween(startDate, endDate, OrderState.APPROVED);
    }

    @Override
    public List<Order> findAllOrdersByCustomerIdBetweenDates(String customerId,LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByCustomerIdAndCreatedDateBetween(customerId,startDate,endDate,OrderState.APPROVED);
    }

    @Override
    public List<Order> findOrdersByCustomerId(String customerId, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Order> orderPage = orderRepository.findByCustomerIdAndOrderState(customerId, paging, OrderState.APPROVED);
        return orderPage.isEmpty() ? Collections.emptyList() : orderPage.getContent();
    }

    @Override
    public List<StatisticDto> monthlyOrderStatisticsForCustomer(String customerId) {
        return orderRepository.monthlyOrderStatisticsForCustomer(customerId,OrderState.APPROVED);
    }

    @Override
    public Long totalOrderCount(String customerId) {
        return orderRepository.totalOrderCount(customerId,OrderState.APPROVED);
    }

    @Override
    public BigDecimal totalAmountOfOrders(String customerId) {
        return orderRepository.totalAmountOfOrders(customerId,OrderState.APPROVED);
    }
}
