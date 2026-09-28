package com.example.waterquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.BankPayment;

public interface BankPaymentRepository
        extends JpaRepository<BankPayment, Long> {
}