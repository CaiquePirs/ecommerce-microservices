package com.caiquepirs.orders.publisher;

import com.caiquepirs.orders.controller.dto.OrderResponseDTO;
import com.caiquepirs.orders.generator.GenerateOrderResponse;
import com.caiquepirs.orders.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPublisher {

    private final GenerateOrderResponse orderResponse;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${service-orders.config.kafka.topics.orders-paid}")
    private String topic;

    public void publish(Order order){
        try {
            OrderResponseDTO orderResponseDTO = orderResponse.mapToResponse(order);
            String json = objectMapper.writeValueAsString(orderResponseDTO);

            kafkaTemplate.send(topic, "orders", json);

        } catch (JsonProcessingException | RuntimeException e) {
            throw new RuntimeException("Error when publishing payment order: " + e.getMessage());
        }
    }
}
