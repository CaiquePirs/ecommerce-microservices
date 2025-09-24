package com.logistic.logistic.service;

import com.logistic.logistic.model.UpdateOrderShipping;
import com.logistic.logistic.model.enuns.StatusOrder;
import com.logistic.logistic.event.publisher.SendOrderPublisher;
import com.logistic.logistic.event.subscriber.representation.UpdateInvoiceOrderRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.random.RandomGenerator;

@Service
@RequiredArgsConstructor
public class ShippingLogisticService {

    private final SendOrderPublisher sendOrderPublisher;

    public void sendTrackingCode(UpdateInvoiceOrderRepresentation updateInvoiceOrder){
        String trackingCode = generateTrackingCode();

        var updateOrderShipping = UpdateOrderShipping.builder()
                .id(updateInvoiceOrder.id())
                .status(StatusOrder.SHIPPED)
                .trackingCode(trackingCode)
                .build();

        sendOrderPublisher.send(updateOrderShipping);
    }

    private String generateTrackingCode(){
        Long randomCode = Random.from(RandomGenerator.getDefault()).nextLong();
        return String.format("LX%dBR", randomCode);
    }

}
