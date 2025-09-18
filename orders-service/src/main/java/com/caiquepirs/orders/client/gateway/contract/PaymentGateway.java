package com.caiquepirs.orders.client.gateway.contract;

import com.caiquepirs.orders.controller.dto.ReceiveCallbackPaymentDTO;
import com.caiquepirs.orders.model.Order;

public interface PaymentGateway {
    void callbackPayment(ReceiveCallbackPaymentDTO receiveCallbackPaymentDTO);
    String pay(Order order);
}
