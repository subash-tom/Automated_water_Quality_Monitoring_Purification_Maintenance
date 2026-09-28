package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.Budget;
import com.example.waterquality.model.LedgerEntry;
import com.example.waterquality.model.PurchaseOrder;
import com.example.waterquality.model.SalesInvoice;
import com.example.waterquality.repository.BudgetRepository;
import com.example.waterquality.repository.LedgerEntryRepository;
import com.example.waterquality.repository.PurchaseOrderRepository;
import com.example.waterquality.repository.SalesInvoiceRepository;

@Service
public class FinanceService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SalesInvoiceRepository salesInvoiceRepository;
    private final BudgetRepository budgetRepository;
    private final LedgerEntryRepository ledgerEntryRepository;

    public FinanceService(
            PurchaseOrderRepository purchaseOrderRepository,
            SalesInvoiceRepository salesInvoiceRepository,
            BudgetRepository budgetRepository,
            LedgerEntryRepository ledgerEntryRepository) {

        this.purchaseOrderRepository = purchaseOrderRepository;
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.budgetRepository = budgetRepository;
        this.ledgerEntryRepository = ledgerEntryRepository;
    }

    // =====================================================
    // PURCHASE ORDER
    // =====================================================

    public PurchaseOrder createPurchaseOrder(
            PurchaseOrder purchaseOrder) {

        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getPurchaseOrders() {

        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder getPurchaseOrderById(Long id) {

        return purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Purchase Order not found with ID: " + id));
    }

    public void deletePurchaseOrder(Long id) {

        purchaseOrderRepository.deleteById(id);
    }

    // =====================================================
    // SALES INVOICE
    // =====================================================

    public SalesInvoice createSalesInvoice(
            SalesInvoice salesInvoice) {

        return salesInvoiceRepository.save(salesInvoice);
    }

    public List<SalesInvoice> getSalesInvoices() {

        return salesInvoiceRepository.findAll();
    }

    public SalesInvoice getSalesInvoiceById(Long id) {

        return salesInvoiceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sales Invoice not found with ID: " + id));
    }

    public void deleteSalesInvoice(Long id) {

        salesInvoiceRepository.deleteById(id);
    }

    // =====================================================
    // BUDGET
    // =====================================================

    public Budget createBudget(Budget budget) {

        return budgetRepository.save(budget);
    }

    public List<Budget> getBudgets() {

        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Long id) {

        return budgetRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Budget not found with ID: " + id));
    }

    public void deleteBudget(Long id) {

        budgetRepository.deleteById(id);
    }

    // =====================================================
    // LEDGER
    // =====================================================

    public LedgerEntry createLedgerEntry(
            LedgerEntry ledgerEntry) {

        return ledgerEntryRepository.save(ledgerEntry);
    }

    public List<LedgerEntry> getLedgerEntries() {

        return ledgerEntryRepository.findAll();
    }

    public LedgerEntry getLedgerEntryById(Long id) {

        return ledgerEntryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Ledger Entry not found with ID: " + id));
    }

    public void deleteLedgerEntry(Long id) {

        ledgerEntryRepository.deleteById(id);
    }
}