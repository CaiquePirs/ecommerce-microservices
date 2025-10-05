package com.logistic.logistic.event.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistic.logistic.service.ShippingLogisticService;
import com.logistic.logistic.event.subscriber.representation.UpdateInvoiceOrderRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvoicingSubscriber {

    private final ObjectMapper objectMapper;
    private final ShippingLogisticService shippingLogisticService;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
                   topics = "${ecommerce.config.kafka.topics.orders-invoiced}")
    public void listener(String json) {
        try {
            var updateInvoiceOrder = objectMapper.readValue(json, UpdateInvoiceOrderRepresentation.class);
            shippingLogisticService.sendTrackingCode(updateInvoiceOrder);

        } catch (Exception e) {
            log.error("Error to generate tracking code: {}", e.getMessage());
        }
    }

}
