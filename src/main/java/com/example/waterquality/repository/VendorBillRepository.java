package com.example.waterquality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.VendorBill;

public interface VendorBillRepository
        extends JpaRepository<VendorBill, Long> {

    List<VendorBill> findByStatusIgnoreCase(String status);
}