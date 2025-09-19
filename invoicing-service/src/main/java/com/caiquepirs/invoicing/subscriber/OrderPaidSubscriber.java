package com.caiquepirs.invoicing.subscriber;

import com.caiquepirs.invoicing.mapper.OrderMapper;
import com.caiquepirs.invoicing.model.Order;
import com.caiquepirs.invoicing.service.InvoiceGeneratorService;
import com.caiquepirs.invoicing.subscriber.representation.OrderRepresentation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPaidSubscriber {

    private final ObjectMapper objectMapper;
    private final InvoiceGeneratorService invoiceGeneratorService;
    private final OrderMapper orderMapper;

    @KafkaListener(groupId = "ecommerce-invoicing", topics = "${ecommerce.config.kafka.topics.orders-paid}")
    public void listener(String json){
        try {
            var orderRepresentation = objectMapper.readValue(json, OrderRepresentation.class);
            Order order = orderMapper.mapOrder(orderRepresentation);

            invoiceGeneratorService.generator(order);

        } catch (Exception e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
