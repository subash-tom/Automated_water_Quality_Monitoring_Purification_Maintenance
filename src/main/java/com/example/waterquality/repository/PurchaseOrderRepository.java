package com.example.waterquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.waterquality.model.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}