package com.caiquepirs.orders.useCases;

import com.caiquepirs.orders.calculator.OrderCalculator;
import com.caiquepirs.orders.client.services.ClientsApiService;
import com.caiquepirs.orders.client.services.representation.ProductRepresentation;
import com.caiquepirs.orders.controller.dto.ItemOrderRequestDTO;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateItemOrderUseCase {

    private final OrderCalculator orderCalculator;
    private final ClientsApiService clientService;

    public List<OrderItem> execute(List<ItemOrderRequestDTO> itemsDTO, Order order){
        return itemsDTO.stream().map(dto -> {
                    ProductRepresentation product = clientService.findProduct(dto.productId());

                    BigDecimal totalItem = orderCalculator.calculateTotalItem(product.unitValue(), dto.quantity());

                    return OrderItem.builder()
                            .productPrice(product.unitValue())
                            .quantity(dto.quantity())
                            .productId(product.id())
                            .total(totalItem)
                            .order(order)
                            .build();
                }).toList();
    }

}
