package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.PurchaseOrder;
import com.example.waterquality.model.Vendor;
import com.example.waterquality.model.VendorBill;
import com.example.waterquality.repository.PurchaseOrderRepository;
import com.example.waterquality.repository.VendorBillRepository;
import com.example.waterquality.repository.VendorRepository;

@Service
public class VendorBillService {

    private final VendorBillRepository vendorBillRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final VendorRepository vendorRepository;

    public VendorBillService(
            VendorBillRepository vendorBillRepository,
            PurchaseOrderRepository purchaseOrderRepository,
            VendorRepository vendorRepository) {

        this.vendorBillRepository = vendorBillRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.vendorRepository = vendorRepository;
    }

    // ==========================================
    // GET ALL BILLS
    // ==========================================

    public List<VendorBill> getAllBills() {
        return vendorBillRepository.findAll();
    }

    // ==========================================
    // GET UNPAID BILLS
    // ==========================================

    public List<VendorBill> getUnpaidBills() {

        return vendorBillRepository
                .findByStatusIgnoreCase("UNPAID");
    }

    // ==========================================
    // GET BILL BY ID
    // ==========================================

    public VendorBill getBillById(Long id) {

        return vendorBillRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Vendor Bill not found: " + id));
    }

    // ==========================================
    // CREATE BILL FROM PURCHASE ORDER
    // ==========================================

    public VendorBill createBill(
            Long purchaseOrderId,
            String billNumber,
            String billDate,
            String status) {

        if (billNumber == null ||
                billNumber.trim().isEmpty()) {

            throw new RuntimeException(
                    "Bill number is required");
        }

        PurchaseOrder purchaseOrder =
                purchaseOrderRepository
                        .findById(purchaseOrderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Purchase Order not found"));
        
        Vendor vendor = vendorRepository.findById(purchaseOrder.getVendorId())
            .orElseThrow(() ->
                new RuntimeException("Vendor not found for Purchase Order"));

        VendorBill bill = new VendorBill();

        bill.setBillNumber(billNumber);
        bill.setBillDate(billDate);

        bill.setPurchaseOrder(purchaseOrder);
        bill.setVendor(vendor);

        // Amount comes from Purchase Order
        bill.setAmount(
                purchaseOrder.getTotalAmount()
        );

        if (status == null ||
                status.trim().isEmpty()) {

            status = "UNPAID";
        }

        bill.setStatus(status.toUpperCase());

        return vendorBillRepository.save(bill);
    }

    // ==========================================
    // SAVE BILL
    // ==========================================

    public VendorBill saveBill(VendorBill bill) {

        if (bill.getStatus() == null ||
                bill.getStatus().trim().isEmpty()) {

            bill.setStatus("UNPAID");
        }

        bill.setStatus(
                bill.getStatus().toUpperCase()
        );

        return vendorBillRepository.save(bill);
    }

    // ==========================================
    // DELETE BILL
    // ==========================================

    public void deleteBill(Long id) {

        if (!vendorBillRepository.existsById(id)) {

            throw new RuntimeException(
                    "Vendor Bill not found: " + id);
        }

        vendorBillRepository.deleteById(id);
    }
}