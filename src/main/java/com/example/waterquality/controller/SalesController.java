package com.example.waterquality.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.Customer;
import com.example.waterquality.model.LedgerEntry;
import com.example.waterquality.model.Plant;
import com.example.waterquality.model.SalesInvoice;
import com.example.waterquality.repository.CustomerRepository;
import com.example.waterquality.repository.LedgerEntryRepository;
import com.example.waterquality.repository.PlantRepository;
import com.example.waterquality.repository.SalesInvoiceRepository;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesInvoiceRepository salesRepository;
    private final PlantRepository plantRepository;
    private final CustomerRepository customerRepository;
    private final LedgerEntryRepository ledgerRepository;

    public SalesController(
            SalesInvoiceRepository salesRepository,
            PlantRepository plantRepository,
            CustomerRepository customerRepository,
            LedgerEntryRepository ledgerRepository) {

        this.salesRepository = salesRepository;
        this.plantRepository = plantRepository;
        this.customerRepository = customerRepository;
        this.ledgerRepository = ledgerRepository;
    }

    // ==============================
    // CREATE CUSTOMER INVOICE
    // ==============================

    @PostMapping
    public SalesInvoice createInvoice(
            @RequestParam Long plantId,
            @RequestParam Long customerId,
            @RequestParam double quantity,
            @RequestParam double rate) {

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() ->
                        new RuntimeException("Plant not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        if (quantity <= 0) {
            throw new RuntimeException(
                    "Quantity must be greater than 0");
        }

        if (rate <= 0) {
            throw new RuntimeException(
                    "Rate must be greater than 0");
        }

        double total = quantity * rate;

        SalesInvoice invoice = new SalesInvoice();

        invoice.setPlant(plant);
        invoice.setCustomer(customer);
        invoice.setQuantity(quantity);
        invoice.setTotalAmount(total);
        invoice.setStatus("INVOICED");

        return salesRepository.save(invoice);
    }

    // ==============================
    // GET ALL INVOICES
    // ==============================

    @GetMapping
    public List<SalesInvoice> getInvoices() {

        return salesRepository.findAll();
    }

    // ==============================
    // GET SINGLE INVOICE
    // ==============================

    @GetMapping("/{id}")
    public SalesInvoice getInvoice(
            @PathVariable Long id) {

        return salesRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Invoice not found"));
    }

    // ==============================
    // RECEIVE CUSTOMER PAYMENT
    // ==============================

    @PutMapping("/{id}/pay")
    public String receivePayment(
            @PathVariable Long id) {

        SalesInvoice invoice = salesRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Invoice not found"));

        // Prevent duplicate payment
        if ("PAID".equalsIgnoreCase(invoice.getStatus())) {

            return "Invoice is already PAID";
        }

        // Update invoice status
        invoice.setStatus("PAID");

        salesRepository.save(invoice);

        // Create ledger entry
        LedgerEntry entry = new LedgerEntry();

        entry.setPlant(invoice.getPlant());
        entry.setType("INCOME");
        entry.setCategory(
                "COMMERCIAL_WATER_REVENUE"
        );
        entry.setAmount(
                invoice.getTotalAmount()
        );
        entry.setDescription(
                "Water supply payment received"
        );

        ledgerRepository.save(entry);

        return "Customer payment received successfully";
    }
}