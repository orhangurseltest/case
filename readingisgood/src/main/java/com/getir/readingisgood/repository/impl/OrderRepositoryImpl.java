package com.getir.readingisgood.repository.impl;

import com.getir.readingisgood.dto.statistic.StatisticDto;
import com.getir.readingisgood.enums.OrderState;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.repository.OrderRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

       @Override
    public List<StatisticDto> monthlyOrderStatisticsForCustomer(String customerId, OrderState orderState) {

        final Aggregation aggregation = newAggregation(
                match(Criteria.where("orderState").is(orderState).and("customerId").is(customerId)),
                project("amount","count").and(DateOperators.Month.month("$createdDate")).as("month"),
                group("month").count().as("totalOrderCount")
                        .sum("count").as("totalBookCount")
                .sum("amount").as("totalAmount"),
                project("totalOrderCount","totalBookCount","totalAmount").and("month")
                        .previousOperation(), //
                sort(Sort.Direction.ASC,"month")
        );
            return mongoTemplate.aggregate(aggregation,Order.class,StatisticDto.class).getMappedResults();
    }
}
