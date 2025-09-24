package com.logistic.logistic.event.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistic.logistic.model.UpdateOrderShipping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendOrderPublisher {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${ecommerce.config.kafka.topics.orders-shipped}")
    private String topic;

    public void send(UpdateOrderShipping updateOrderShipping){
        try {
            String json = objectMapper.writeValueAsString(updateOrderShipping);

            kafkaTemplate.send(topic, "shipped", json);

        } catch (Exception e){
            throw new RuntimeException("error when publishing order tracking code: " + e.getMessage());
        }
    }


}
