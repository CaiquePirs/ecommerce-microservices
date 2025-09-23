package com.caiquepirs.orders.client.gateway.strategy.factory;

import com.caiquepirs.orders.client.gateway.strategy.PaymentMethodStrategy;
import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.model.enums.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentMethodFactory {

    private final List<PaymentMethodStrategy> strategies;

    public Order execute(Order order, PaymentType paymentType){
        for(PaymentMethodStrategy paymentMethodStrategy : strategies){
            order = paymentMethodStrategy.pay(order, paymentType);
        }
        return order;
    }

}
