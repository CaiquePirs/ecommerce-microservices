package com.caiquepirs.orders.model;

import com.caiquepirs.orders.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

    private String data;
    private PaymentType paymentType;
}
