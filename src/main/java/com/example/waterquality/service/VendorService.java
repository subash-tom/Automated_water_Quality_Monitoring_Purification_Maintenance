package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.Vendor;
import com.example.waterquality.repository.VendorRepository;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    // Get all vendors
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    // Get vendor by ID
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vendor not found with id: " + id));
    }

    // Create vendor
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    // Update vendor
    public Vendor updateVendor(Long id, Vendor vendor) {

        Vendor existingVendor = getVendorById(id);

        existingVendor.setName(vendor.getName());
        existingVendor.setContactPerson(vendor.getContactPerson());
        existingVendor.setPhone(vendor.getPhone());
        existingVendor.setEmail(vendor.getEmail());
        existingVendor.setAddress(vendor.getAddress());

        return vendorRepository.save(existingVendor);
    }

    // Delete vendor
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}