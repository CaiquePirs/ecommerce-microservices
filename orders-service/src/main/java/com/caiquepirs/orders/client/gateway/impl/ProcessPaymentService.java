package com.caiquepirs.orders.client.gateway.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProcessPaymentService {

    public String execute(){
        return UUID.randomUUID().toString();
    }

}
