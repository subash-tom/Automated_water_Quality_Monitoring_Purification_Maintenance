package com.example.waterquality.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.LedgerEntry;
import com.example.waterquality.repository.LedgerEntryRepository;

@Service
public class LedgerEntryService {

    private final LedgerEntryRepository ledgerEntryRepository;

    public LedgerEntryService(LedgerEntryRepository ledgerEntryRepository) {
        this.ledgerEntryRepository = ledgerEntryRepository;
    }

    public List<LedgerEntry> getAllEntries() {
        return ledgerEntryRepository.findAll();
    }

    public LedgerEntry getEntryById(Long id) {
        return ledgerEntryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ledger entry not found"));
    }

    public LedgerEntry createEntry(
            String type,
            String category,
            Double amount,
            String description) {

        LedgerEntry entry = new LedgerEntry();

        entry.setType(type);
        entry.setCategory(category);
        entry.setAmount(amount);
        entry.setDescription(description);
        entry.setEntryDate(LocalDateTime.now());

        return ledgerEntryRepository.save(entry);
    }

    public LedgerEntry updateEntry(
            Long id,
            String type,
            String category,
            Double amount,
            String description) {

        LedgerEntry entry = getEntryById(id);

        entry.setType(type);
        entry.setCategory(category);
        entry.setAmount(amount);
        entry.setDescription(description);

        return ledgerEntryRepository.save(entry);
    }

    public void deleteEntry(Long id) {
        ledgerEntryRepository.deleteById(id);
    }

    public List<LedgerEntry> getIncomeEntries() {
        return ledgerEntryRepository.findByType("INCOME");
    }

    public List<LedgerEntry> getExpenseEntries() {
        return ledgerEntryRepository.findByType("EXPENSE");
    }
}