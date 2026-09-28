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

import com.example.waterquality.model.Vendor;
import com.example.waterquality.service.VendorService;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // ==============================
    // GET ALL VENDORS
    // GET /api/vendors
    // ==============================

    @GetMapping
    public List<Vendor> getVendors() {
        return vendorService.getAllVendors();
    }

    // ==============================
    // GET VENDOR BY ID
    // GET /api/vendors/{id}
    // ==============================

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendor(
            @PathVariable Long id) {

        try {

            Vendor vendor = vendorService.getVendorById(id);

            return ResponseEntity.ok(vendor);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    // ==============================
    // CREATE VENDOR
    // POST /api/vendors
    // ==============================

    @PostMapping
    public Vendor createVendor(
            @RequestBody Vendor vendor) {

        return vendorService.createVendor(vendor);
    }

    // ==============================
    // UPDATE VENDOR
    // PUT /api/vendors/{id}
    // ==============================

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(
            @PathVariable Long id,
            @RequestBody Vendor vendor) {

        try {

            Vendor updatedVendor =
                    vendorService.updateVendor(id, vendor);

            return ResponseEntity.ok(updatedVendor);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    // ==============================
    // DELETE VENDOR
    // DELETE /api/vendors/{id}
    // ==============================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(
            @PathVariable Long id) {

        try {

            vendorService.deleteVendor(id);

            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }
}