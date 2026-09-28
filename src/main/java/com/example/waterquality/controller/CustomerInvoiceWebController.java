package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.waterquality.service.CustomerInvoiceService;
import com.example.waterquality.service.SalesOrderService;

@Controller
@RequestMapping("/customer-invoices")
public class CustomerInvoiceWebController {

    private final CustomerInvoiceService invoiceService;
    private final SalesOrderService salesOrderService;

    public CustomerInvoiceWebController(
            CustomerInvoiceService invoiceService,
            SalesOrderService salesOrderService) {

        this.invoiceService = invoiceService;
        this.salesOrderService = salesOrderService;
    }

    // VIEW ALL INVOICES
    @GetMapping
    public String invoices(Model model) {

        model.addAttribute(
                "invoices",
                invoiceService.getAllInvoices()
        );

        return "customer-invoices";
    }

    // ADD INVOICE PAGE
    @GetMapping("/add")
    public String addInvoicePage(Model model) {

        model.addAttribute(
                "salesOrders",
                salesOrderService.getAllOrders()
        );

        return "customer-invoice-form";
    }

    // SAVE INVOICE
    @PostMapping("/save")
    public String saveInvoice(
            @RequestParam Long salesOrderId,
            @RequestParam String invoiceNumber,
            @RequestParam String invoiceDate) {

        invoiceService.createInvoice(
                salesOrderId,
                invoiceNumber,
                invoiceDate
        );

        return "redirect:/customer-invoices";
    }

    // DELETE INVOICE
    @GetMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable Long id) {

        invoiceService.deleteInvoice(id);

        return "redirect:/customer-invoices";
    }
}