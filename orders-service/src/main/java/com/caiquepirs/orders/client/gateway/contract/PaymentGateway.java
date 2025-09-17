package com.caiquepirs.orders.client.gateway.contract;

import com.caiquepirs.orders.controller.dto.ReceiveCallbackPaymentDTO;
import com.caiquepirs.orders.model.Order;

public interface PaymentGateway {
    String paymentCode(Order order);
    void updateStatusPayment(ReceiveCallbackPaymentDTO receiveCallbackPaymentDTO);
}
