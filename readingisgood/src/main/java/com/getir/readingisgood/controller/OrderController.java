package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.order.AddOrderDto;
import com.getir.readingisgood.dto.order.OrderDto;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<OrderDto> createNewOrder(@Valid @RequestBody AddOrderDto addOrderDto) {
        Order order = orderService.addOrder(addOrderDto);

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);

        return ResponseEntity.ok(orderDto);
    }

    @GetMapping({"/list", "/list/{customerId}"})
    public ResponseEntity<List<OrderDto>> findOrdersBetweenDates(
            @PathVariable String customerId,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {

        List<Order> orders;
        if (customerId != null)
            orders = orderService.findAllOrdersByCustomerIdBetweenDates(customerId, startDate, endDate);

        else
            orders = orderService.findAllOrdersBetweenDates(startDate, endDate);
        return ResponseEntity.ok(this.convertOrderListToDtoList(orders));
    }


    @GetMapping("order/{customerId}")
    public ResponseEntity<List<OrderDto>> findCustomerOrders(
            @PathVariable String customerId,
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "3") int pageSize) {

        List<Order> orders = orderService.findOrdersByCustomerId(customerId, pageNo, pageSize);
        return ResponseEntity.ok(this.convertOrderListToDtoList(orders));
    }

    private List<OrderDto> convertOrderListToDtoList(List<Order> orders) {
        List<OrderDto> orderDtos = orders.stream().map(order -> {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order, orderDto);
            return orderDto;
        }).collect(Collectors.toList());
        return orderDtos;
    }


}
