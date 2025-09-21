package com.caiquepirs.orders.useCases;

import com.caiquepirs.orders.calculator.OrderCalculator;
import com.caiquepirs.orders.client.gateway.strategy.factory.PaymentMethodFactory;
import com.caiquepirs.orders.client.services.ClientsApiService;
import com.caiquepirs.orders.controller.dto.OrderRequestDTO;
import com.caiquepirs.orders.mapper.OrderMapper;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.OrderItem;
import com.caiquepirs.orders.model.enums.StatusOrder;
import com.caiquepirs.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository repository;
    private final PaymentMethodFactory paymentMethodFactory;
    private final OrderCalculator calculator;
    private final OrderMapper orderMapper;
    private final CreateItemOrderUseCase createItemOrderUseCase;
    private final ClientsApiService clientService;

    public Order execute(OrderRequestDTO dto) {
        Order order = orderMapper.toEntity(dto);
        List<OrderItem> items = createItemOrderUseCase.execute(dto.items(), order);

        clientService.findCustomer(order.getCustomerId());

        String paymentKey = paymentMethodFactory.pay(order);

        order.setItems(items);
        order.setTotal(calculator.calculateTotalOrder(order));
        order.setPaymentKey(paymentKey);
        order.setStatus(StatusOrder.PLACED);

        items.forEach(item -> clientService.updateProductStock(
                item.getProductId(), item.getQuantity())
        );

       return repository.save(order);
    }

}
