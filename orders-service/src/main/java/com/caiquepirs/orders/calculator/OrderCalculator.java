package com.caiquepirs.orders.calculator;

import com.caiquepirs.orders.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderCalculator {

    public BigDecimal calculateTotalOrder(Order order){
      return order.getItems().stream()
                .map(item -> item.getUnitValue().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .abs();
    }
}
