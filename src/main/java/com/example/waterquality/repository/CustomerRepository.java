package com.example.waterquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}