package com.example.waterquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.CustomerInvoice;

public interface CustomerInvoiceRepository
        extends JpaRepository<CustomerInvoice, Long> {
}