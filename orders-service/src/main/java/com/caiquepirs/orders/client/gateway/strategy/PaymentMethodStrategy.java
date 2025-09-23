package com.caiquepirs.orders.client.gateway.strategy;

import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.enums.PaymentType;

public interface PaymentMethodStrategy {
    Order pay(Order order, PaymentType paymentType);
}
