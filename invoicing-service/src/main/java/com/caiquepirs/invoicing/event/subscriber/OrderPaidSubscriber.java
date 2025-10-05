package com.caiquepirs.invoicing.event.subscriber;

import com.caiquepirs.invoicing.mapper.OrderMapper;
import com.caiquepirs.invoicing.model.Order;
import com.caiquepirs.invoicing.event.publisher.InvoicingPublisher;
import com.caiquepirs.invoicing.service.InvoicingService;
import com.caiquepirs.invoicing.event.subscriber.representation.OrderRepresentation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
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
            log.error("Failed to process order message: {}", json, e);
        }
    }

}
