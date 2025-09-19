package com.caiquepirs.invoicing.mapper;

import com.caiquepirs.invoicing.model.Customer;
import com.caiquepirs.invoicing.model.ItemOrder;
import com.caiquepirs.invoicing.model.Order;
import com.caiquepirs.invoicing.subscriber.representation.OrderRepresentation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderMapper {

    public Order mapOrder(OrderRepresentation orderRepresentation){
        Customer customer = mapToCustomer(orderRepresentation);
        List<ItemOrder> items = mapToItems(orderRepresentation);

        BigDecimal totalOrder = items.stream()
                .map(ItemOrder::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Order.builder()
                .id(orderRepresentation.id())
                .customer(customer)
                .itemOrders(items)
                .total(totalOrder)
                .orderDate(orderRepresentation.orderDate())
                .build();
    }

    private List<ItemOrder> mapToItems(OrderRepresentation orderRepresentation){
        return orderRepresentation.items().stream()
                .map(i -> ItemOrder.builder()
                        .id(i.id())
                        .description(i.productName())
                        .unitValue(i.unitValue())
                        .quantity(i.quantity())
                        .build()).toList();
    }

    private Customer mapToCustomer(OrderRepresentation orderRepresentation){
        return Customer.builder()
                .name(orderRepresentation.name())
                .phone(orderRepresentation.phone())
                .cpf(orderRepresentation.cpf())
                .email(orderRepresentation.email())
                .neighborhood(orderRepresentation.neighborhood())
                .number(orderRepresentation.number())
                .street(orderRepresentation.street())
                .build();
    }

}
