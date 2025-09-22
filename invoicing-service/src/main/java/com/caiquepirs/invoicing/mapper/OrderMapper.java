package com.caiquepirs.invoicing.mapper;

import com.caiquepirs.invoicing.model.Address;
import com.caiquepirs.invoicing.model.Customer;
import com.caiquepirs.invoicing.model.ItemOrder;
import com.caiquepirs.invoicing.model.Order;
import com.caiquepirs.invoicing.subscriber.representation.CustomerAddressRepresentation;
import com.caiquepirs.invoicing.subscriber.representation.CustomerRepresentation;
import com.caiquepirs.invoicing.subscriber.representation.OrderRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public Order mapOrder(OrderRepresentation orderRepresentation){
        Customer customer = mapToCustomer(orderRepresentation.customer());
        List<ItemOrder> items = mapToItems(orderRepresentation);

        return Order.builder()
                .id(orderRepresentation.id())
                .orderDate(orderRepresentation.orderDate())
                .itemOrders(items)
                .customer(customer)
                .total(orderRepresentation.total())
                .build();
    }

    private List<ItemOrder> mapToItems(OrderRepresentation orderRepresentation){
        return orderRepresentation.items().stream()
                .map(i -> ItemOrder.builder()
                        .id(i.id())
                        .productName(i.productName())
                        .unitValue(i.unitValue())
                        .quantity(i.quantity())
                        .total(i.total())
                        .build()).toList();
    }

    private Customer mapToCustomer(CustomerRepresentation customerRepresentation){
        return Customer.builder()
                .name(customerRepresentation.name())
                .lastName(customerRepresentation.lastName())
                .phone(customerRepresentation.phone())
                .email(customerRepresentation.email())
                .cpf(customerRepresentation.cpf())
                .address(mapToAddress(customerRepresentation.address()))
                .build();
    }

    private Address mapToAddress(CustomerAddressRepresentation addressRepresentation){
        return Address.builder()
                .city(addressRepresentation.city())
                .state(addressRepresentation.state())
                .country(addressRepresentation.country())
                .neighborhood(addressRepresentation.neighborhood())
                .number(addressRepresentation.number())
                .street(addressRepresentation.street())
                .build();
    }

}
