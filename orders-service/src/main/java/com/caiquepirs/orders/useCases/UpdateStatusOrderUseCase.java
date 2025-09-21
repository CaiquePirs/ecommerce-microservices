package com.caiquepirs.orders.useCases;

import com.caiquepirs.orders.model.Order;
import com.caiquepirs.orders.repository.OrderRepository;
import com.caiquepirs.orders.subscriber.representation.UpdateStatusOrderRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStatusOrderUseCase {

    private final FindOrderByIdUseCase findOrderByIdUseCase;
    private final OrderRepository orderRepository;

    public void execute(UpdateStatusOrderRepresentation updateOrder){
        Order order = findOrderByIdUseCase.execute(updateOrder.id());

        if(updateOrder.status() != null) {
            order.setStatus(updateOrder.status());
        }

        if(updateOrder.urlInvoice() != null) {
            order.setInvoiceUrl(updateOrder.urlInvoice());
        }

        if(updateOrder.trackingCode() != null) {
            order.setTrackingCode(updateOrder.trackingCode());
        }

        orderRepository.save(order);
    }

}
