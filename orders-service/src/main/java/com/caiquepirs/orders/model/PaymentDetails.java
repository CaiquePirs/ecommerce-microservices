package com.caiquepirs.orders.model;

import com.caiquepirs.orders.model.enums.PaymentType;
import lombok.Data;

@Data
public class PaymentDetails {

    private String data;
    private PaymentType paymentType;
}
