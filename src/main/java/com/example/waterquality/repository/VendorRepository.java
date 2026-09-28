package com.example.waterquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.waterquality.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}