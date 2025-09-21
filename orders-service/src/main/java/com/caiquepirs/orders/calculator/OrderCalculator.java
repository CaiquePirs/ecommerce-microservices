package com.caiquepirs.orders.calculator;

import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.OrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderCalculator {

    public BigDecimal calculateTotalOrder(Order order){
      return order.getItems().stream()
              .map(OrderItem::getTotal)
              .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalItem(BigDecimal unitValue, Integer quantity){
        if (unitValue == null || quantity == null) {
            return BigDecimal.ZERO;
        }
        return unitValue.multiply(BigDecimal.valueOf(quantity));
    }

}
