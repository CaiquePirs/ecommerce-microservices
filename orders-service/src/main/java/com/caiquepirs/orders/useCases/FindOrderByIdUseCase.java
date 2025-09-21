package com.caiquepirs.orders.useCases;

import com.caiquepirs.orders.controller.handler.exceptions.OrderNotFoundException;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindOrderByIdUseCase {

    private final OrderRepository repository;

    public Order execute(Long orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order ID not found"));
    }

}
