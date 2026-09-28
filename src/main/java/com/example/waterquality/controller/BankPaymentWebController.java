package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.waterquality.service.BankPaymentService;
import com.example.waterquality.service.VendorBillService;

@Controller
@RequestMapping("/bank-payments")
public class BankPaymentWebController {

    private final BankPaymentService bankPaymentService;
    private final VendorBillService vendorBillService;

    public BankPaymentWebController(
            BankPaymentService bankPaymentService,
            VendorBillService vendorBillService) {

        this.bankPaymentService = bankPaymentService;
        this.vendorBillService = vendorBillService;
    }

    // =====================================================
    // SHOW ALL BANK PAYMENTS
    // URL: /bank-payments
    // =====================================================

    @GetMapping
    public String payments(Model model) {

        model.addAttribute(
                "payments",
                bankPaymentService.getAllPayments()
        );

        return "bank-payments";
    }

    // =====================================================
    // SHOW ADD PAYMENT FORM
    // URL: /bank-payments/add
    // =====================================================

    @GetMapping("/add")
    public String addPayment(Model model) {

        model.addAttribute(
                "unpaidBills",
                vendorBillService.getUnpaidBills()
        );

        return "bank-payment-form";
    }

    // =====================================================
    // SAVE BANK PAYMENT
    // URL: /bank-payments/save
    // =====================================================

    @PostMapping("/save")
    public String savePayment(

            @RequestParam("vendorBillId")
            Long vendorBillId,

            @RequestParam("paymentDate")
            String paymentDate,

            @RequestParam("paymentMethod")
            String paymentMethod,

            @RequestParam("transactionNumber")
            String transactionNumber) {

        bankPaymentService.createPayment(
                vendorBillId,
                paymentDate,
                paymentMethod,
                transactionNumber
        );

        return "redirect:/bank-payments";
    }

    // =====================================================
    // DELETE PAYMENT
    // URL: /bank-payments/delete/{id}
    // =====================================================

    @GetMapping("/delete/{id}")
    public String deletePayment(
            @PathVariable("id") Long id) {

        bankPaymentService.deletePayment(id);

        return "redirect:/bank-payments";
    }
}