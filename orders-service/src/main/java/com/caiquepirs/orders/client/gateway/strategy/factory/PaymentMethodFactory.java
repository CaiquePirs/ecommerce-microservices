package com.caiquepirs.orders.client.gateway.strategy.factory;

import com.caiquepirs.orders.client.gateway.strategy.impl.BoletoPaymentImplStrategy;
import com.caiquepirs.orders.client.gateway.strategy.impl.CreditPaymentImpStrategy;
import com.caiquepirs.orders.client.gateway.strategy.impl.DebitPaymentImpStrategy;
import com.caiquepirs.orders.client.gateway.strategy.impl.PixPaymentImpStrategy;
import com.caiquepirs.orders.controller.handler.exceptions.PaymentErrorException;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.PaymentDetails;
import com.caiquepirs.orders.model.enums.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentMethodFactory {

    private final BoletoPaymentImplStrategy boletoPaymentImpl;
    private final PixPaymentImpStrategy pixPaymentImp;
    private final CreditPaymentImpStrategy creditPaymentImp;
    private final DebitPaymentImpStrategy debitPaymentImp;

    public String pay(Order order){
        PaymentDetails paymentDetails = Optional.of(order.getPaymentDetails())
                .orElseThrow(() -> new PaymentErrorException("Payment error. Payment details cannot be null"));

        PaymentType paymentType = Optional.of(paymentDetails.getPaymentType())
                .orElseThrow(() -> new PaymentErrorException("Payment error. Payment type cannot be null"));

        switch (paymentType){
            case PIX -> {
                return pixPaymentImp.pay(order);
            }
            case BOLETO -> {
                return boletoPaymentImpl.pay(order);
            }
            case CREDIT -> {
                return creditPaymentImp.pay(order);
            }
            case DEBIT -> {
                return debitPaymentImp.pay(order);
            }
            default -> throw new PaymentErrorException("Payment method not found");
        }
    }
}
