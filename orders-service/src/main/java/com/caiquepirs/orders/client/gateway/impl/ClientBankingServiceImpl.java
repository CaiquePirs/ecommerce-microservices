package com.caiquepirs.orders.client.gateway.impl;

import com.caiquepirs.orders.client.gateway.contract.PaymentGateway;
import com.caiquepirs.orders.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ClientBankingServiceImpl implements PaymentGateway {

    @Override
    public String paymentCode(Order order){
        log.info("Request payment for the order id: {}", order.getId());
        return UUID.randomUUID().toString();
    }
}
