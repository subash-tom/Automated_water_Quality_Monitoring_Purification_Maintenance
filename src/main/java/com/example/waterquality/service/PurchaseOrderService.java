package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.PurchaseOrder;
import com.example.waterquality.repository.PurchaseOrderRepository;
import com.example.waterquality.repository.VendorRepository;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final VendorRepository vendorRepository;

    public PurchaseOrderService(
            PurchaseOrderRepository purchaseOrderRepository,
            VendorRepository vendorRepository) {

        this.purchaseOrderRepository = purchaseOrderRepository;
        this.vendorRepository = vendorRepository;
    }

    // ==========================================
    // GET ALL PURCHASE ORDERS
    // ==========================================

    public List<PurchaseOrder> getAllPurchaseOrders() {

        return purchaseOrderRepository.findAll();
    }

    // ==========================================
    // GET PURCHASE ORDER BY ID
    // ==========================================

    public PurchaseOrder getPurchaseOrderById(Long id) {

        return purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Purchase Order not found with id: " + id
                        )
                );
    }

    // ==========================================
    // CREATE PURCHASE ORDER
    // ==========================================

    public PurchaseOrder createPurchaseOrder(
            PurchaseOrder purchaseOrder) {

        validatePurchaseOrder(purchaseOrder);

        return purchaseOrderRepository.save(purchaseOrder);
    }

    // ==========================================
    // UPDATE PURCHASE ORDER
    // ==========================================

    public PurchaseOrder updatePurchaseOrder(
            Long id,
            PurchaseOrder purchaseOrder) {

        PurchaseOrder existing =
                getPurchaseOrderById(id);

        validatePurchaseOrder(purchaseOrder);

        existing.setOrderNumber(
                purchaseOrder.getOrderNumber()
        );

        existing.setVendorId(
                purchaseOrder.getVendorId()
        );

        existing.setOrderDate(
                purchaseOrder.getOrderDate()
        );

        existing.setExpectedDate(
                purchaseOrder.getExpectedDate()
        );

        existing.setTotalAmount(
                purchaseOrder.getTotalAmount()
        );

        existing.setStatus(
                purchaseOrder.getStatus()
        );

        existing.setDescription(
                purchaseOrder.getDescription()
        );

        return purchaseOrderRepository.save(existing);
    }

    // ==========================================
    // DELETE
    // ==========================================

    public void deletePurchaseOrder(Long id) {

        if (!purchaseOrderRepository.existsById(id)) {

            throw new RuntimeException(
                    "Purchase Order not found with id: " + id
            );
        }

        purchaseOrderRepository.deleteById(id);
    }

    // ==========================================
    // VALIDATION
    // ==========================================

    private void validatePurchaseOrder(
            PurchaseOrder purchaseOrder) {

        if (purchaseOrder == null) {

            throw new RuntimeException(
                    "Purchase Order data is required"
            );
        }

        // Order Number
        if (purchaseOrder.getOrderNumber() == null ||
                purchaseOrder.getOrderNumber().trim().isEmpty()) {

            throw new RuntimeException(
                    "Order number is required"
            );
        }

        // Vendor
        if (purchaseOrder.getVendorId() == null) {

            throw new RuntimeException(
                    "Please select a vendor"
            );
        }

        // Check vendor exists
        if (!vendorRepository.existsById(
                purchaseOrder.getVendorId())) {

            throw new RuntimeException(
                    "Selected vendor does not exist"
            );
        }

        // Amount
        if (purchaseOrder.getTotalAmount() == null) {

            throw new RuntimeException(
                    "Total amount is required"
            );
        }

        if (purchaseOrder.getTotalAmount() < 0) {

            throw new RuntimeException(
                    "Total amount cannot be negative"
            );
        }

        // Status
        if (purchaseOrder.getStatus() == null ||
                purchaseOrder.getStatus().trim().isEmpty()) {

            purchaseOrder.setStatus("PENDING");
        }
    }
}