package com.caiquepirs.orders.service;

import com.caiquepirs.orders.calculator.OrderCalculator;
import com.caiquepirs.orders.controller.dto.OrderRequestDTO;
import com.caiquepirs.orders.mapper.OrderItemMapper;
import com.caiquepirs.orders.mapper.OrderMapper;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.OrderItem;
import com.caiquepirs.orders.model.enums.StatusOrder;
import com.caiquepirs.orders.validator.OrderValidator;
import com.caiquepirs.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderValidator validator;
    private final OrderCalculator calculator;
    private final OrderMapper orderMapper;
    private final OrderItemMapper itemMapper;

    public Order order(OrderRequestDTO orderRequestDTO){
        Order order = orderMapper.toEntity(orderRequestDTO);

        List<OrderItem> items = orderRequestDTO.items()
                .stream()
                .map(itemMapper::toEntity)
                .toList();

        items.forEach(i -> i.setOrder(order));
        order.setItems(items);
        BigDecimal totalOrder = calculator.calculateTotalOrder(order);

        order.setTotal(totalOrder);
        order.setStatus(StatusOrder.PLACED);

       return repository.save(order);
    }

}
