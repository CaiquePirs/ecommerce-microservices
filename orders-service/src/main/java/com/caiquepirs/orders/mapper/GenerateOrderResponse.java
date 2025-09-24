package com.caiquepirs.orders.mapper;

import com.caiquepirs.orders.calculator.OrderCalculator;
import com.caiquepirs.orders.client.services.ClientsApiService;
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
    private final ClientsApiService clientsService;

    public OrderResponseDTO mapToResponse(Order order) {
        var customer = clientsService.findCustomer(order.getCustomerId());

        return OrderResponseDTO.builder()
                .id(order.getId())
                .customer(customer)
                .orderDate(order.getOrderDate())
                .total(calculator.calculateTotalOrder(order))
                .items(mapToItems(order))
                .status(order.getStatus())
                .paymentKey(order.getPaymentKey())
                .invoiceUrl(order.getInvoiceUrl())
                .trackingCode(order.getTrackingCode())
                .build();
    }

    private List<ItemOrderResponseDTO> mapToItems(Order order) {
        return order.getItems().stream().map(item -> {
            var product = clientsService.findProduct(item.getProductId());

            return ItemOrderResponseDTO.builder()
                    .id(item.getId())
                    .productName(product.name())
                    .unitValue(item.getProductPrice())
                    .quantity(item.getQuantity())
                    .total(item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .build();
        }).toList();
    }

}
