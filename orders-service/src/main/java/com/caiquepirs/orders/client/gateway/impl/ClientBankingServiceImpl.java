package com.caiquepirs.orders.client.gateway.impl;

import com.caiquepirs.orders.client.gateway.contract.PaymentGateway;
import com.caiquepirs.orders.controller.dto.ReceiveCallbackPaymentDTO;
import com.caiquepirs.orders.controller.handler.exceptions.OrderNotFoundException;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.enums.StatusOrder;
import com.caiquepirs.orders.publisher.PaymentPublisher;
import com.caiquepirs.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientBankingServiceImpl implements PaymentGateway {

    private final OrderRepository orderRepository;
    private final PaymentPublisher paymentPublisher;

    public String pay(Order order){
        return UUID.randomUUID().toString();
    }

    @Override
    public void callbackPayment(ReceiveCallbackPaymentDTO receiveCallbackPaymentDTO) {
        Long orderId = receiveCallbackPaymentDTO.orderId();
        String paymentKey = receiveCallbackPaymentDTO.paymentKey();

        Order order = orderRepository.findByIdAndPaymentKey(orderId, paymentKey)
                .orElseThrow(() -> new OrderNotFoundException(
                        "Order with ID: " + orderId +
                        " and PaymentKey: " + paymentKey + " not found.")
                );

        if(receiveCallbackPaymentDTO.status()){
            order.setStatus(StatusOrder.PAID);
            order.setNotes(receiveCallbackPaymentDTO.notes());

            paymentPublisher.publish(order);

        } else {
            order.setStatus(StatusOrder.PAYMENT_ERROR);
            order.setNotes(receiveCallbackPaymentDTO.notes());
        }

        orderRepository.save(order);
    }
}
