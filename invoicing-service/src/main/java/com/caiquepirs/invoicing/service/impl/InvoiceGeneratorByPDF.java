package com.caiquepirs.invoicing.service.impl;

import com.caiquepirs.invoicing.controller.advice.exceptions.FailedToGenerateFileException;
import com.caiquepirs.invoicing.model.Order;
import com.caiquepirs.invoicing.service.contract.InvoiceGenerator;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InvoiceGeneratorByPDF implements InvoiceGenerator<byte[]> {

    @Value("classpath:reports/invoicing.jrxml")
    private Resource invoicing;

    @Override
    public byte[] generator(Order order){
        try (InputStream inputStream = invoicing.getInputStream()) {

            Map<String, Object> params = new HashMap<>();
            params.put("NAME", order.customer().name());
            params.put("CPF", order.customer().cpf());
            params.put("NEIGHBORHOOD", order.customer().neighborhood());
            params.put("NUMBER", order.customer().number());
            params.put("STREET", order.customer().street());
            params.put("EMAIL", order.customer().email());
            params.put("PHONE", order.customer().phone());
            params.put("ORDER_DATE", order.orderDate());
            params.put("TOTAL", order.total());

            var dataSource = new JRBeanCollectionDataSource(order.itemOrders());

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e){
            throw new FailedToGenerateFileException("Error generating PDF: " + e.getMessage());
        }
    }

}
