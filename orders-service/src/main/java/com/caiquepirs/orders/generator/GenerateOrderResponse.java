package com.caiquepirs.orders.generator;

import com.caiquepirs.orders.calculator.OrderCalculator;
import com.caiquepirs.orders.client.services.ClientsRepresentationService;
import com.caiquepirs.orders.client.services.representation.CustomerRepresentationDTO;
import com.caiquepirs.orders.controller.dto.ItemOrderResponseDTO;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.controller.dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenerateOrderResponse {

    private final OrderCalculator calculator;
    private final ClientsRepresentationService clientsService;

    public OrderResponseDTO mapToResponse(Order order) {
        CustomerRepresentationDTO customerDTO = clientsService.findCustomer(order.getCustomerId());

        return OrderResponseDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .name(customerDTO.name())
                .cpf(customerDTO.cpf())
                .email(customerDTO.email())
                .phone(customerDTO.phone())
                .neighborhood(customerDTO.neighborhood())
                .number(customerDTO.number())
                .street(customerDTO.street())
                .orderDate(order.getOrderDate())
                .total(calculator.calculateTotalOrder(order))
                .items(mapToItems(order))
                .status(order.getStatus())
                .invoiceUrl(order.getInvoiceUrl())
                .trackingCode(order.getTrackingCode())
                .build();
    }

    private List<ItemOrderResponseDTO> mapToItems(Order order){
       return order.getItems().stream()
               .map(item -> ItemOrderResponseDTO.builder()
                        .id(item.getId())
                        .productName(clientsService.findProduct(item.getProductId()).name())
                        .unitValue(item.getUnitValue())
                        .quantity(item.getQuantity())
                        .total(item.getUnitValue().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .build()).toList();
    }

}
