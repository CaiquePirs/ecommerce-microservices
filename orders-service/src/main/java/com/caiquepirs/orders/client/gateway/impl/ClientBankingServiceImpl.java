package com.caiquepirs.orders.client.gateway.impl;

import com.caiquepirs.orders.client.gateway.contract.PaymentGateway;
import com.caiquepirs.orders.controller.dto.ReceiveCallbackPayment;
import com.caiquepirs.orders.controller.handler.exceptions.ValidationException;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.enums.StatusOrder;
import com.caiquepirs.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientBankingServiceImpl implements PaymentGateway {

    private final OrderRepository orderRepository;

    @Override
    public String paymentCode(Order order){
        log.info("Request payment for the order id: {}", order.getId());
        return UUID.randomUUID().toString();
    }

    @Override
    public void updateStatusPayment(ReceiveCallbackPayment receiveCallbackPayment) {
        Long orderId = receiveCallbackPayment.orderId();
        String paymentKey = receiveCallbackPayment.paymentKey();

        Order order = orderRepository.findByIdAndPaymentKey(orderId, paymentKey)
                .orElseThrow(() -> new ValidationException(
                        "Order with ID: " + orderId +
                        " and PaymentKey: " + paymentKey + " not found.")
                );

        if(receiveCallbackPayment.status()){
            order.setStatus(StatusOrder.PAID);
        } else {
            order.setStatus(StatusOrder.PAYMENT_ERROR);
            order.setNotes(receiveCallbackPayment.notes());
        }

        orderRepository.save(order);
    }
}
