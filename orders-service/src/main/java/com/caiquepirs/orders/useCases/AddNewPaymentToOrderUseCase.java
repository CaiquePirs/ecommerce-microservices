package com.caiquepirs.orders.useCases;

import com.caiquepirs.orders.client.gateway.strategy.factory.PaymentMethodFactory;
import com.caiquepirs.orders.controller.dto.UpdateOrderPaymentDTO;
import com.caiquepirs.orders.controller.handler.exceptions.ValidationException;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.PaymentDetails;
import com.caiquepirs.orders.model.enums.StatusOrder;
import com.caiquepirs.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddNewPaymentToOrderUseCase {

    private final OrderRepository repository;
    private final FindOrderByIdUseCase findOrderByIdUseCase;
    private final PaymentMethodFactory paymentMethodFactory;

    @Transactional
    public void execute(UpdateOrderPaymentDTO paymentDTO) {
        Order order = findOrderByIdUseCase.execute(paymentDTO.orderId());

        if (order.getStatus().equals(StatusOrder.PAID)) {
            throw new ValidationException("This order is already paid and cannot be changed.");
        }

        PaymentDetails paymentDetails = PaymentDetails
                .builder()
                .paymentType(paymentDTO.paymentType())
                .data(paymentDTO.paymentData())
                .build();

        String paymentKey = paymentMethodFactory.pay(order);

        order.setPaymentDetails(paymentDetails);
        order.setPaymentKey(paymentKey);
        order.setStatus(StatusOrder.PLACED);
        order.setNotes("New payment added to order");

        repository.save(order);
    }

}
