package com.caiquepirs.invoicing.service.contract;

import com.caiquepirs.invoicing.model.Order;

public interface InvoiceGeneratorByPdf {
    byte[] generator(Order order);
}
