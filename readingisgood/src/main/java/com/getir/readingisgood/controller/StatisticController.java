package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.statistic.StatisticDto;
import com.getir.readingisgood.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    private final OrderService orderService;

    @PostMapping("order/{customerId}")
    public ResponseEntity<List<StatisticDto>> findCustomerMonthlyStatistics(@PathVariable String customerId){
        return ResponseEntity.ok(orderService.monthlyOrderStatisticsForCustomer(customerId));
    }

}
