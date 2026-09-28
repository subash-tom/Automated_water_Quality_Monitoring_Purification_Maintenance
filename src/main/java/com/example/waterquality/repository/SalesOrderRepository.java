package com.example.waterquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
}