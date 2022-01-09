package com.getir.readingisgood.repository;

import com.getir.readingisgood.enums.OrderState;
import com.getir.readingisgood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface OrderRepository extends MongoRepository<Order, String> ,OrderRepositoryCustom{

    List<Order> findByCreatedDateBetween(LocalDate startDate, LocalDate finishDate, OrderState orderState);

    List<Order> findByCustomerIdAndCreatedDateBetween(String customerId,LocalDate startDate, LocalDate finishDate, OrderState orderState);

    Page<Order> findByCustomerIdAndOrderState(String customerId, Pageable pageable, OrderState orderState);

    @Query(value="{customerId: ?0, orderState: ?1}", count = true)
    Long totalOrderCount(String customerId, OrderState orderState);

    @Aggregation(pipeline= {"{$match: { customerId: ?0, orderState: ?1 }}","{$group: { _id: '', total: {$sum: $amount}}}"})
    BigDecimal totalAmountOfOrders(String customerId, OrderState orderState);

}
