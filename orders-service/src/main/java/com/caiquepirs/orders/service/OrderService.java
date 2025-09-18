package com.caiquepirs.orders.service;

import com.caiquepirs.orders.calculator.OrderCalculator;
import com.caiquepirs.orders.client.gateway.impl.ClientBankingServiceImpl;
import com.caiquepirs.orders.client.gateway.strategy.factory.PaymentMethodFactory;
import com.caiquepirs.orders.client.services.ClientsRepresentationService;
import com.caiquepirs.orders.controller.dto.OrderRequestDTO;
import com.caiquepirs.orders.controller.dto.UpdateOrderPaymentDTO;
import com.caiquepirs.orders.controller.handler.exceptions.OrderNotFoundException;
import com.caiquepirs.orders.controller.handler.exceptions.ValidationException;
import com.caiquepirs.orders.mapper.ItemOrderMapper;
import com.caiquepirs.orders.mapper.OrderMapper;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.OrderItem;
import com.caiquepirs.orders.model.PaymentDetails;
import com.caiquepirs.orders.model.enums.StatusOrder;
import com.caiquepirs.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final PaymentMethodFactory paymentMethodFactory;
    private final OrderCalculator calculator;
    private final OrderMapper orderMapper;
    private final ItemOrderMapper itemMapper;
    private final ClientsRepresentationService clientService;

    @Transactional
    public void order(OrderRequestDTO orderRequestDTO) {
        Order order = orderMapper.toEntity(orderRequestDTO);

        clientService.findCustomer(order.getCustomerId());
        order.getItems().forEach(i -> clientService.findProduct(i.getProductId()));

        List<OrderItem> items = orderRequestDTO.items()
                .stream()
                .map(itemMapper::toEntity)
                .toList();

        items.forEach(i -> i.setOrder(order));

        String paymentKey = paymentMethodFactory.pay(order);

        order.setItems(items);
        order.setTotal(calculator.calculateTotalOrder(order));
        order.setPaymentKey(paymentKey);
        order.setStatus(StatusOrder.PLACED);

        repository.save(order);
    }

    public Order findOrderById(Long orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order ID not found"));
    }

    @Transactional
    public void addNewPayment(UpdateOrderPaymentDTO updateOrderPaymentDTO) {
        Order order = findOrderById(updateOrderPaymentDTO.orderId());

        if (order.getStatus().equals(StatusOrder.PAID)) {
            throw new ValidationException("This order is already paid and cannot be changed.");
        }

        PaymentDetails paymentDetails = PaymentDetails
                .builder()
                .paymentType(updateOrderPaymentDTO.paymentType())
                .data(updateOrderPaymentDTO.paymentData())
                .build();

        String paymentKey = paymentMethodFactory.pay(order);

        order.setPaymentDetails(paymentDetails);
        order.setPaymentKey(paymentKey);
        order.setStatus(StatusOrder.PLACED);
        order.setNotes("New payment added to order");

        repository.save(order);
    }

}
