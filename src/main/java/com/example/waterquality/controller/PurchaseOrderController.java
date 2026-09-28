package com.example.waterquality.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.PurchaseOrder;
import com.example.waterquality.service.PurchaseOrderService;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(
            PurchaseOrderService purchaseOrderService) {

        this.purchaseOrderService = purchaseOrderService;
    }

    // GET ALL
    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrders() {

        return purchaseOrderService.getAllPurchaseOrders();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrder(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    purchaseOrderService
                            .getPurchaseOrderById(id)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> createPurchaseOrder(
            @RequestBody PurchaseOrder purchaseOrder) {

        try {

            return ResponseEntity.ok(
                    purchaseOrderService
                            .createPurchaseOrder(purchaseOrder)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePurchaseOrder(
            @PathVariable Long id,
            @RequestBody PurchaseOrder purchaseOrder) {

        try {

            return ResponseEntity.ok(
                    purchaseOrderService
                            .updatePurchaseOrder(
                                    id,
                                    purchaseOrder
                            )
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchaseOrder(
            @PathVariable Long id) {

        try {

            purchaseOrderService
                    .deletePurchaseOrder(id);

            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }
}