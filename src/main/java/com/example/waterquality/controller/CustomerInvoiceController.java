package com.example.waterquality.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.CustomerInvoice;
import com.example.waterquality.service.CustomerInvoiceService;

@RestController
@RequestMapping("/api/customer-invoices")
public class CustomerInvoiceController {

    private final CustomerInvoiceService invoiceService;

    public CustomerInvoiceController(
            CustomerInvoiceService invoiceService) {

        this.invoiceService = invoiceService;
    }

    // GET ALL INVOICES
    @GetMapping
    public List<CustomerInvoice> getAllInvoices() {

        return invoiceService.getAllInvoices();
    }

    // GET ONE INVOICE
    @GetMapping("/{id}")
    public CustomerInvoice getInvoice(
            @PathVariable Long id) {

        return invoiceService.getInvoiceById(id);
    }

    // CREATE INVOICE FROM SALES ORDER
    @PostMapping
    public CustomerInvoice createInvoice(
            @RequestParam Long salesOrderId,
            @RequestParam String invoiceNumber,
            @RequestParam String invoiceDate) {

        return invoiceService.createInvoice(
                salesOrderId,
                invoiceNumber,
                invoiceDate
        );
    }

    // DELETE INVOICE
    @DeleteMapping("/{id}")
    public String deleteInvoice(
            @PathVariable Long id) {

        invoiceService.deleteInvoice(id);

        return "Customer Invoice deleted successfully";
    }
}