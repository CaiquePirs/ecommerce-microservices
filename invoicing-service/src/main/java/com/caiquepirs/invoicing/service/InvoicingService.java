package com.caiquepirs.invoicing.service;

import com.caiquepirs.invoicing.bucket.BucketFile;
import com.caiquepirs.invoicing.bucket.BucketService;
import com.caiquepirs.invoicing.controller.advice.exceptions.FailedToGenerateFileException;
import com.caiquepirs.invoicing.model.Order;
import com.caiquepirs.invoicing.service.impl.InvoiceGeneratorByPDF;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class InvoicingService {

    private final InvoiceGeneratorByPDF invoiceGeneratorByPDF;
    private final BucketService bucketService;

    public void generateInvoiceByPdf(Order order) {
        try {
            byte[] reportPdf = invoiceGeneratorByPDF.generator(order);

            BucketFile file = BucketFile.builder()
                    .name(String.format("order_invoicing_%d.pdf", order.id()))
                    .inputStream(new ByteArrayInputStream(reportPdf))
                    .mediaType(MediaType.APPLICATION_PDF)
                    .size(Long.valueOf(reportPdf.length))
                    .build();

            bucketService.upload(file);

        } catch (Exception e) {
            throw new FailedToGenerateFileException("Fail to generate invoicing order PDF: " + e.getMessage());
        }
    }
}
