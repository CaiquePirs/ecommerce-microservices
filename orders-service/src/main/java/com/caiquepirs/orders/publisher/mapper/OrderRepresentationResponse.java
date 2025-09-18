package com.caiquepirs.orders.publisher.mapper;

import com.caiquepirs.orders.calculator.OrderCalculator;
import com.caiquepirs.orders.client.services.representation.CustomerRepresentationDTO;
import com.caiquepirs.orders.client.services.representation.ProductRepresentationDTO;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.publisher.representation.ItemOrderRepresentation;
import com.caiquepirs.orders.publisher.representation.OrderRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderRepresentationResponse {

    private final OrderCalculator calculator;

    public OrderRepresentation mapToRepresentation(CustomerRepresentationDTO customerRepresentationDTO,
                                                   ProductRepresentationDTO productRepresentationDTO, Order order) {
        return OrderRepresentation.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .name(customerRepresentationDTO.name())
                .cpf(customerRepresentationDTO.cpf())
                .email(customerRepresentationDTO.email())
                .phone(customerRepresentationDTO.phone())
                .neighborhood(customerRepresentationDTO.neighborhood())
                .number(customerRepresentationDTO.number())
                .street(customerRepresentationDTO.street())
                .orderDate(order.getOrderDate())
                .total(calculator.calculateTotalOrder(order))
                .items(mapToItems(order, productRepresentationDTO))
                .status(order.getStatus())
                .build();
    }

    private List<ItemOrderRepresentation> mapToItems(Order order, ProductRepresentationDTO productRepresentation){
       return order.getItems().stream()
               .map(item -> ItemOrderRepresentation.builder()
                        .id(item.getId())
                        .productName(productRepresentation.name())
                        .unitValue(item.getUnitValue())
                        .quantity(item.getQuantity())
                        .total(item.getUnitValue().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .build()).toList();
    }

}
