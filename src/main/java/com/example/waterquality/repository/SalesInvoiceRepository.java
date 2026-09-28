package com.example.waterquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.SalesInvoice;

public interface SalesInvoiceRepository
        extends JpaRepository<SalesInvoice, Long> {
}