package com.example.waterquality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.waterquality.model.LedgerEntry;

public interface LedgerEntryRepository
        extends JpaRepository<LedgerEntry, Long> {

    List<LedgerEntry> findByType(String type);
}