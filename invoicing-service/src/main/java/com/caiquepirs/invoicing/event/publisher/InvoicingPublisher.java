package com.caiquepirs.invoicing.event.publisher;

import com.caiquepirs.invoicing.bucket.BucketService;
import com.caiquepirs.invoicing.controller.advice.exceptions.FileNotFoundException;
import com.caiquepirs.invoicing.model.Order;
import com.caiquepirs.invoicing.event.subscriber.representation.enuns.StatusOrder;
import com.caiquepirs.invoicing.event.subscriber.representation.UpdateStatusOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvoicingPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final BucketService bucketService;
    private final ObjectMapper objectMapper;

    @Value("${ecommerce.config.kafka.topics.orders-invoiced}")
    private String topic;

    public void publisher(Order order){
       try {
           String url = bucketService.getFileUrl(String.format("order_invoicing_%d.pdf", order.id()));

           if(url == null || url.isBlank()){
               throw new FileNotFoundException("Invoice URL not found for order ID: " + order.id());
           }

           var updateStatusOrder = UpdateStatusOrder.builder()
                   .id(order.id())
                   .status(StatusOrder.INVOICED)
                   .urlInvoice(url)
                   .build();

           String json = objectMapper.writeValueAsString(updateStatusOrder);

           kafkaTemplate.send(topic, "invoicing", json);

       } catch (Exception e){
           log.error("Failed to publisher order message: {}", e.getMessage());
       }

    }

}
