package com.caiquepirs.invoicing.service.contract;

import com.caiquepirs.invoicing.model.Order;

public interface InvoiceGenerator<T> {
    T generator(Order order);
}
