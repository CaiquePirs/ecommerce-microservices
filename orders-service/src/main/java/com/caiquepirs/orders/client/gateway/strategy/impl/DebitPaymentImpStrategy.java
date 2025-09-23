package com.caiquepirs.orders.client.gateway.strategy.impl;

import com.caiquepirs.orders.client.gateway.impl.ProcessPaymentService;
import com.caiquepirs.orders.client.gateway.strategy.PaymentMethodStrategy;
import com.caiquepirs.orders.client.gateway.impl.ClientBankingServiceImpl;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.enums.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DebitPaymentImpStrategy implements PaymentMethodStrategy {

    private final ProcessPaymentService processPaymentService;

    @Override
    public Order pay(Order order, PaymentType paymentType) {
        if(paymentType.equals(PaymentType.DEBIT)){

            String paymentKey = processPaymentService.execute();
            order.setPaymentKey(paymentKey);

            return order;
        }
        return order;
    }
}
