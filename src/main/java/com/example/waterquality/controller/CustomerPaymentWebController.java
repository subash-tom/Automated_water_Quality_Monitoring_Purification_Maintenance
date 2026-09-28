package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.waterquality.service.CustomerInvoiceService;
import com.example.waterquality.service.CustomerPaymentService;

@Controller
@RequestMapping("/customer-payments")
public class CustomerPaymentWebController {

    private final CustomerPaymentService paymentService;
    private final CustomerInvoiceService invoiceService;

    public CustomerPaymentWebController(
            CustomerPaymentService paymentService,
            CustomerInvoiceService invoiceService) {

        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
    }

    // PAYMENT LIST
    @GetMapping
    public String payments(Model model) {

        model.addAttribute(
                "payments",
                paymentService.getAllPayments()
        );

        return "customer-payments";
    }

    // ADD PAYMENT PAGE
    @GetMapping("/add")
    public String addPaymentPage(Model model) {

        model.addAttribute(
                "invoices",
                invoiceService.getAllInvoices()
        );

        return "customer-payment-form";
    }

    // SAVE PAYMENT
    @PostMapping("/save")
    public String savePayment(

            @RequestParam Long invoiceId,
            @RequestParam String paymentDate,
            @RequestParam String paymentMethod,
            @RequestParam String referenceNumber) {

        paymentService.createPayment(
                invoiceId,
                paymentDate,
                paymentMethod,
                referenceNumber
        );

        return "redirect:/customer-payments";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deletePayment(
            @PathVariable Long id) {

        paymentService.deletePayment(id);

        return "redirect:/customer-payments";
    }
}