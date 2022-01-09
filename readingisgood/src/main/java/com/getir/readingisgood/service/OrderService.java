package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.order.AddOrderDto;
import com.getir.readingisgood.dto.statistic.StatisticDto;
import com.getir.readingisgood.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    Order addOrder(AddOrderDto addOrderDto);

    List<Order> findAllOrdersByCustomerIdBetweenDates(String customerId,LocalDate startDate, LocalDate endDate);

    List<Order> findAllOrdersBetweenDates(LocalDate startDate, LocalDate endDate);

    List<Order> findOrdersByCustomerId(String customerId, int pageNo, int pageSize);

    Long totalOrderCount(String customerId);

    BigDecimal totalAmountOfOrders(String customerId);

    List<StatisticDto> monthlyOrderStatisticsForCustomer(String customerId);
}
