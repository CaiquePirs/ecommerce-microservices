package com.caiquepirs.orders.useCases;

import com.caiquepirs.orders.controller.handler.exceptions.OrderNotFoundException;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindOrderByPaymentKeyUseCase {

    private final OrderRepository orderRepository;

    public Order order(Long orderId, String paymentKey) {
        return orderRepository.findByIdAndPaymentKey(orderId, paymentKey)
                .orElseThrow(() -> new OrderNotFoundException(
                        "Order with ID: " + orderId +
                        " and PaymentKey: " + paymentKey + " not found.")
                );

    }
}
