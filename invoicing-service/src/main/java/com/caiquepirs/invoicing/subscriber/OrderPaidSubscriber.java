package com.caiquepirs.invoicing.subscriber;

import com.caiquepirs.invoicing.mapper.OrderMapper;
import com.caiquepirs.invoicing.model.Order;
import com.caiquepirs.invoicing.service.InvoicingService;
import com.caiquepirs.invoicing.subscriber.representation.OrderRepresentation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPaidSubscriber {

    private final ObjectMapper objectMapper;
    private final InvoicingService invoicingService;
    private final InvoicingPublisher invoicingPublisher;
    private final OrderMapper orderMapper;

    @KafkaListener(groupId = "ecommerce-invoicing", topics = "${ecommerce.config.kafka.topics.orders-paid}")
    public void listener(String json){
        try {
            var orderRepresentation = objectMapper.readValue(json, OrderRepresentation.class);
            Order order = orderMapper.mapOrder(orderRepresentation);

            invoicingService.generateInvoiceByPdf(order);
            invoicingPublisher.publisher(order);

        } catch (Exception e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
