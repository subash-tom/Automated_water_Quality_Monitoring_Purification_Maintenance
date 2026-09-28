package com.example.waterquality.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.waterquality.repository.CustomerRepository;
import com.example.waterquality.repository.LedgerEntryRepository;
import com.example.waterquality.repository.PlantRepository;
import com.example.waterquality.repository.ProductRepository;
import com.example.waterquality.repository.PurchaseOrderRepository;
import com.example.waterquality.repository.SalesInvoiceRepository;
import com.example.waterquality.repository.VendorRepository;

@Service
public class DashboardService {

    private final PlantRepository plantRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SalesInvoiceRepository salesInvoiceRepository;
    private final LedgerEntryRepository ledgerEntryRepository;

    public DashboardService(
            PlantRepository plantRepository,
            CustomerRepository customerRepository,
            VendorRepository vendorRepository,
            ProductRepository productRepository,
            PurchaseOrderRepository purchaseOrderRepository,
            SalesInvoiceRepository salesInvoiceRepository,
            LedgerEntryRepository ledgerEntryRepository) {

        this.plantRepository = plantRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        this.productRepository = productRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.salesInvoiceRepository = salesInvoiceRepository;
        this.ledgerEntryRepository = ledgerEntryRepository;
    }

    public Map<String, Object> getDashboardData() {

        long totalPlants = plantRepository.count();
        long totalCustomers = customerRepository.count();
        long totalVendors = vendorRepository.count();
        long totalProducts = productRepository.count();
        long totalPurchaseOrders = purchaseOrderRepository.count();
        long totalSalesInvoices = salesInvoiceRepository.count();

        double totalIncome = ledgerEntryRepository
                .findByType("INCOME")
                .stream()
                .filter(entry -> entry.getAmount() != null)
                .mapToDouble(entry -> entry.getAmount())
                .sum();

        double totalExpense = ledgerEntryRepository
                .findByType("EXPENSE")
                .stream()
                .filter(entry -> entry.getAmount() != null)
                .mapToDouble(entry -> entry.getAmount())
                .sum();

        double netProfit = totalIncome - totalExpense;

        Map<String, Object> dashboard = new HashMap<>();

        dashboard.put("totalPlants", totalPlants);
        dashboard.put("totalCustomers", totalCustomers);
        dashboard.put("totalVendors", totalVendors);
        dashboard.put("totalProducts", totalProducts);
        dashboard.put("totalPurchaseOrders", totalPurchaseOrders);
        dashboard.put("totalSalesInvoices", totalSalesInvoices);
        dashboard.put("totalIncome", totalIncome);
        dashboard.put("totalExpense", totalExpense);
        dashboard.put("netProfit", netProfit);

        return dashboard;
    }
}