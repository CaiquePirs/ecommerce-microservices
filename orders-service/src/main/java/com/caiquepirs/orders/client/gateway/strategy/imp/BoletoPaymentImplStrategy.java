package com.caiquepirs.orders.client.gateway.strategy.imp;

import com.caiquepirs.orders.client.gateway.strategy.PaymentMethodStrategy;
import com.caiquepirs.orders.client.gateway.impl.ClientBankingServiceImpl;
import com.caiquepirs.orders.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoletoPaymentImplStrategy implements PaymentMethodStrategy {

    private final ClientBankingServiceImpl clientBankingService;

    @Override
    public String pay(Order order) {
        return clientBankingService.pay(order);
    }
}
