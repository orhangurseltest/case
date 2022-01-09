package com.getir.readingisgood.model;

import com.getir.readingisgood.enums.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document("customers")
public class Order extends BaseEntity {

    @Id
    private String orderId;

    private String customerId;
    private String bookId;
    private Integer count;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal amount;

    private OrderState orderState = OrderState.REJECTED; // default order status
}
