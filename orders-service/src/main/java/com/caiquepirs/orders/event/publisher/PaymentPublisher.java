package com.caiquepirs.orders.event.publisher;

import com.caiquepirs.orders.controller.dto.OrderResponseDTO;
import com.caiquepirs.orders.mapper.GenerateOrderResponse;
import com.caiquepirs.orders.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
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
            log.info("Error when publishing payment order topic: {}", e.getMessage());
        }
    }
}
