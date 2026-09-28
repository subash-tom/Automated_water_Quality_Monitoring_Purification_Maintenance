package com.example.waterquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}