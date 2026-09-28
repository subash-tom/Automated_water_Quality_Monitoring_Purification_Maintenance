package com.example.waterquality.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.BankPayment;
import com.example.waterquality.service.BankPaymentService;

@RestController
@RequestMapping("/api/bank-payments")
public class BankPaymentController {

    private final BankPaymentService bankPaymentService;

    public BankPaymentController(
            BankPaymentService bankPaymentService) {

        this.bankPaymentService = bankPaymentService;
    }

    // =====================================================
    // GET ALL BANK PAYMENTS
    // URL: GET /api/bank-payments
    // =====================================================

    @GetMapping
    public ResponseEntity<List<BankPayment>> getAllPayments() {

        List<BankPayment> payments =
                bankPaymentService.getAllPayments();

        return ResponseEntity.ok(payments);
    }

    // =====================================================
    // GET PAYMENT BY ID
    // URL: GET /api/bank-payments/{id}
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<BankPayment> getPaymentById(
            @PathVariable("id") Long id) {

        BankPayment payment =
                bankPaymentService.getPaymentById(id);

        return ResponseEntity.ok(payment);
    }

    // =====================================================
    // CREATE BANK PAYMENT
    // URL:
    // POST /api/bank-payments
    // =====================================================

    @PostMapping
    public ResponseEntity<BankPayment> createPayment(

            @RequestParam("vendorBillId")
            Long vendorBillId,

            @RequestParam("paymentDate")
            String paymentDate,

            @RequestParam("paymentMethod")
            String paymentMethod,

            @RequestParam("transactionNumber")
            String transactionNumber) {

        BankPayment payment =
                bankPaymentService.createPayment(
                        vendorBillId,
                        paymentDate,
                        paymentMethod,
                        transactionNumber
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(payment);
    }

    // =====================================================
    // DELETE BANK PAYMENT
    // URL: DELETE /api/bank-payments/{id}
    // =====================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(
            @PathVariable("id") Long id) {

        bankPaymentService.deletePayment(id);

        return ResponseEntity.ok(
                "Bank Payment deleted successfully"
        );
    }
}