package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.CustomerInvoice;
import com.example.waterquality.model.CustomerPayment;
import com.example.waterquality.model.LedgerEntry;
import com.example.waterquality.repository.CustomerInvoiceRepository;
import com.example.waterquality.repository.CustomerPaymentRepository;
import com.example.waterquality.repository.LedgerEntryRepository;

@Service
public class CustomerPaymentService {

    private final CustomerPaymentRepository paymentRepository;
    private final CustomerInvoiceRepository invoiceRepository;
    private final LedgerEntryRepository ledgerEntryRepository;

    public CustomerPaymentService(
            CustomerPaymentRepository paymentRepository,
            CustomerInvoiceRepository invoiceRepository,
            LedgerEntryRepository ledgerEntryRepository) {

        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
        this.ledgerEntryRepository = ledgerEntryRepository;
    }

    // GET ALL PAYMENTS
    public List<CustomerPayment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // GET PAYMENT BY ID
    public CustomerPayment getPaymentById(Long id) {

        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer Payment not found"));
    }

    // CREATE PAYMENT
    public CustomerPayment createPayment(
            Long invoiceId,
            String paymentDate,
            String paymentMethod,
            String referenceNumber) {

        CustomerInvoice invoice =
                invoiceRepository.findById(invoiceId)
                        .orElseThrow(() ->
                                new RuntimeException("Customer Invoice not found"));

        // Prevent duplicate payment
        if ("PAID".equalsIgnoreCase(invoice.getStatus())) {

            throw new RuntimeException(
                    "This invoice is already PAID");
        }

        // Create payment
        CustomerPayment payment = new CustomerPayment();

        payment.setInvoice(invoice);
        payment.setPaymentDate(paymentDate);
        payment.setPaymentMethod(paymentMethod);
        payment.setReferenceNumber(referenceNumber);

        // Amount comes automatically from invoice
        payment.setAmount(invoice.getAmount());

        CustomerPayment savedPayment =
                paymentRepository.save(payment);

        // Update invoice status
        invoice.setStatus("PAID");

        invoiceRepository.save(invoice);

        // Create income ledger entry
        LedgerEntry ledger = new LedgerEntry();

        ledger.setType("INCOME");
        ledger.setCategory("COMMERCIAL_WATER_REVENUE");
        ledger.setAmount(invoice.getAmount());

        ledger.setDescription(
                "Bank payment for Customer Invoice "
                        + invoice.getInvoiceNumber()
        );

        ledgerEntryRepository.save(ledger);

        return savedPayment;
    }

    // DELETE PAYMENT
    public void deletePayment(Long id) {

        paymentRepository.deleteById(id);
    }
}