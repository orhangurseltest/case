package com.getir.readingisgood.repository;

import com.getir.readingisgood.dto.statistic.StatisticDto;
import com.getir.readingisgood.enums.OrderState;

import java.util.List;

public interface OrderRepositoryCustom {
    List<StatisticDto> monthlyOrderStatisticsForCustomer(String customerId, OrderState orderState);
}
