package com.example.waterquality.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.VendorBill;
import com.example.waterquality.service.VendorBillService;

@RestController
@RequestMapping("/api/vendor-bills")
public class VendorBillController {

    private final VendorBillService vendorBillService;

    public VendorBillController(
            VendorBillService vendorBillService) {

        this.vendorBillService = vendorBillService;
    }

    // GET ALL
    @GetMapping
    public List<VendorBill> getAllBills() {

        return vendorBillService.getAllBills();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<VendorBill> getBill(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    vendorBillService
                            .getBillById(id)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> createBill(
            @RequestParam Long purchaseOrderId,
            @RequestParam String billNumber,
            @RequestParam String billDate,
            @RequestParam(required = false)
            String status) {

        try {

            return ResponseEntity.ok(
                    vendorBillService.createBill(
                            purchaseOrderId,
                            billNumber,
                            billDate,
                            status
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBill(
            @PathVariable Long id) {

        try {

            vendorBillService.deleteBill(id);

            return ResponseEntity.noContent()
                    .build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound()
                    .build();
        }
    }
}