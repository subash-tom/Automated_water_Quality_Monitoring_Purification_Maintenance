package com.example.waterquality.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.CustomerPayment;
import com.example.waterquality.service.CustomerPaymentService;

@RestController
@RequestMapping("/api/customer-payments")
public class CustomerPaymentController {

    private final CustomerPaymentService paymentService;

    public CustomerPaymentController(
            CustomerPaymentService paymentService) {

        this.paymentService = paymentService;
    }

    // GET ALL
    @GetMapping
    public List<CustomerPayment> getAllPayments() {

        return paymentService.getAllPayments();
    }

    // GET ONE
    @GetMapping("/{id}")
    public CustomerPayment getPayment(
            @PathVariable Long id) {

        return paymentService.getPaymentById(id);
    }

    // CREATE PAYMENT
    @PostMapping
    public CustomerPayment createPayment(

            @RequestParam Long invoiceId,
            @RequestParam String paymentDate,
            @RequestParam String paymentMethod,
            @RequestParam String referenceNumber) {

        return paymentService.createPayment(
                invoiceId,
                paymentDate,
                paymentMethod,
                referenceNumber
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deletePayment(
            @PathVariable Long id) {

        paymentService.deletePayment(id);

        return "Customer Payment deleted successfully";
    }
}