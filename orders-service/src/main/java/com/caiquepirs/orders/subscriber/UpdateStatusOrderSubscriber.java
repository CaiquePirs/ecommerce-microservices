package com.caiquepirs.orders.subscriber;

import com.caiquepirs.orders.controller.handler.exceptions.ValidationException;
import com.caiquepirs.orders.subscriber.representation.UpdateStatusOrderRepresentation;
import com.caiquepirs.orders.useCases.UpdateStatusOrderUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateStatusOrderSubscriber {

    private final UpdateStatusOrderUseCase updateStatusOrderUseCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            groupId = "ecommerce-updated-order",
            topics = {
                    "${service-orders.config.kafka.topics.orders-shipped}",
                    "${service-orders.config.kafka.topics.orders-invoiced}"
            })
    public void receiveOrderUpdate(String json){
        try {
            var updateStatusOrder = objectMapper.readValue(
                    json,
                    UpdateStatusOrderRepresentation.class);

            updateStatusOrderUseCase.execute(updateStatusOrder);

        } catch (Exception e){
            throw new ValidationException("error receiving order update: " + e.getMessage());
        }
    }

}
