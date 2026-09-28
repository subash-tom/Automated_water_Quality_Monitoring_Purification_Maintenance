package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.CustomerInvoice;
import com.example.waterquality.model.SalesOrder;
import com.example.waterquality.repository.CustomerInvoiceRepository;
import com.example.waterquality.repository.SalesOrderRepository;

@Service
public class CustomerInvoiceService {

    private final CustomerInvoiceRepository invoiceRepository;
    private final SalesOrderRepository salesOrderRepository;

    public CustomerInvoiceService(
            CustomerInvoiceRepository invoiceRepository,
            SalesOrderRepository salesOrderRepository) {

        this.invoiceRepository = invoiceRepository;
        this.salesOrderRepository = salesOrderRepository;
    }

    public List<CustomerInvoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public CustomerInvoice getInvoiceById(Long id) {

        return invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer Invoice not found"));
    }

    public CustomerInvoice createInvoice(
            Long salesOrderId,
            String invoiceNumber,
            String invoiceDate) {

        SalesOrder salesOrder = salesOrderRepository.findById(salesOrderId)
                .orElseThrow(() ->
                        new RuntimeException("Sales Order not found"));

        CustomerInvoice invoice = new CustomerInvoice();

        invoice.setSalesOrder(salesOrder);
        invoice.setCustomer(salesOrder.getCustomer());

        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDate(invoiceDate);

        // Invoice amount comes from Sales Order
        invoice.setAmount(salesOrder.getTotalAmount());

        // New invoice starts as unpaid
        invoice.setStatus("UNPAID");

        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}