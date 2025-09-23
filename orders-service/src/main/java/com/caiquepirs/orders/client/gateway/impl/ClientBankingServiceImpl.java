package com.caiquepirs.orders.client.gateway.impl;

import com.caiquepirs.orders.client.gateway.contract.PaymentGateway;
import com.caiquepirs.orders.client.gateway.strategy.factory.PaymentMethodFactory;
import com.caiquepirs.orders.controller.dto.ReceiveCallbackPaymentDTO;
import com.caiquepirs.orders.controller.handler.exceptions.PaymentErrorException;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.enums.PaymentType;
import com.caiquepirs.orders.model.enums.StatusOrder;
import com.caiquepirs.orders.publisher.PaymentPublisher;
import com.caiquepirs.orders.repository.OrderRepository;
import com.caiquepirs.orders.useCases.FindOrderByPaymentKeyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ClientBankingServiceImpl implements PaymentGateway {

    private final OrderRepository orderRepository;
    private final PaymentPublisher paymentPublisher;
    private final FindOrderByPaymentKeyUseCase findOrderByPaymentKeyUseCase;
    private final PaymentMethodFactory paymentMethodFactory;

    public String pay(Order order) {
        PaymentType paymentType = order.getPaymentDetails().getPaymentType();

        String paymentKey = paymentMethodFactory.execute(order, paymentType).getPaymentKey();

        if(paymentKey == null){
            throw new PaymentErrorException("Error processing payment");
        }

        return paymentKey;
    }

    @Override
    public void callbackPayment(ReceiveCallbackPaymentDTO receiveCallbackPaymentDTO) {
        Long orderId = receiveCallbackPaymentDTO.orderId();
        String paymentKey = receiveCallbackPaymentDTO.paymentKey();

        Order order = findOrderByPaymentKeyUseCase.order(orderId, paymentKey);

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
