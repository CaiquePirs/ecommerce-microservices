package com.caiquepirs.orders.model;

import com.caiquepirs.orders.model.enums.PaymentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDetails {

    private String data;
    private PaymentType paymentType;
}
