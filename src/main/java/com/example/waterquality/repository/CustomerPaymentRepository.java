package com.example.waterquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.CustomerPayment;

public interface CustomerPaymentRepository
        extends JpaRepository<CustomerPayment, Long> {
}