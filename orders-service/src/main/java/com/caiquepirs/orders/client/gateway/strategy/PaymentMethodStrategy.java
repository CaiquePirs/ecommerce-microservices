package com.caiquepirs.orders.client.gateway.strategy;

import com.caiquepirs.orders.model.Order;

public interface PaymentMethodStrategy {
    String pay(Order order);
}
