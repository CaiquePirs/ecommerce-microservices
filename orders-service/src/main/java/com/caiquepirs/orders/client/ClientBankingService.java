package com.caiquepirs.orders.client;

import com.caiquepirs.orders.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ClientBankingService {

    public String requestPayment(Order order){
        log.info("Request payment for the order id: {}", order.getId());
        return UUID.randomUUID().toString();
    }
}
