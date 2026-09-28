package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.BankPayment;
import com.example.waterquality.model.VendorBill;
import com.example.waterquality.repository.BankPaymentRepository;
import com.example.waterquality.repository.VendorBillRepository;

@Service
public class BankPaymentService {

    private final BankPaymentRepository bankPaymentRepository;
    private final VendorBillRepository vendorBillRepository;

    public BankPaymentService(
            BankPaymentRepository bankPaymentRepository,
            VendorBillRepository vendorBillRepository) {

        this.bankPaymentRepository = bankPaymentRepository;
        this.vendorBillRepository = vendorBillRepository;
    }

    public List<BankPayment> getAllPayments() {
        return bankPaymentRepository.findAll();
    }

    public BankPayment getPaymentById(Long id) {

        return bankPaymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bank Payment not found: " + id));
    }

    public BankPayment createPayment(
            Long vendorBillId,
            String paymentDate,
            String paymentMethod,
            String transactionNumber) {

        VendorBill bill =
                vendorBillRepository.findById(vendorBillId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Vendor Bill not found: "
                                                + vendorBillId));

        if (!"UNPAID".equalsIgnoreCase(
                bill.getStatus())) {

            throw new RuntimeException(
                    "Vendor Bill is already paid");
        }

        BankPayment payment = new BankPayment();

        payment.setVendorBill(bill);
        payment.setPaymentDate(paymentDate);
        payment.setPaymentMethod(paymentMethod);
        payment.setTransactionNumber(transactionNumber);

        Double amount = bill.getAmount();

        if (amount == null) {
            amount = 0.0;
        }

        payment.setAmount(amount);

        BankPayment saved =
                bankPaymentRepository.save(payment);

        // Mark Vendor Bill as PAID
        bill.setStatus("PAID");

        vendorBillRepository.save(bill);

        return saved;
    }

    public void deletePayment(Long id) {

        if (!bankPaymentRepository.existsById(id)) {

            throw new RuntimeException(
                    "Bank Payment not found: " + id);
        }

        bankPaymentRepository.deleteById(id);
    }
}