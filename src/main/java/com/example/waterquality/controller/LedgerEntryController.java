package com.example.waterquality.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.LedgerEntry;
import com.example.waterquality.service.LedgerEntryService;

@RestController
@RequestMapping("/api/ledger")
public class LedgerEntryController {

    private final LedgerEntryService ledgerEntryService;

    public LedgerEntryController(LedgerEntryService ledgerEntryService) {
        this.ledgerEntryService = ledgerEntryService;
    }

    @GetMapping
    public List<LedgerEntry> getAllEntries() {
        return ledgerEntryService.getAllEntries();
    }

    @GetMapping("/{id}")
    public LedgerEntry getEntry(@PathVariable Long id) {
        return ledgerEntryService.getEntryById(id);
    }

    @PostMapping
    public LedgerEntry createEntry(
            @RequestParam String type,
            @RequestParam String category,
            @RequestParam Double amount,
            @RequestParam String description) {

        return ledgerEntryService.createEntry(
                type,
                category,
                amount,
                description
        );
    }

    @PutMapping("/{id}")
    public LedgerEntry updateEntry(
            @PathVariable Long id,
            @RequestParam String type,
            @RequestParam String category,
            @RequestParam Double amount,
            @RequestParam String description) {

        return ledgerEntryService.updateEntry(
                id,
                type,
                category,
                amount,
                description
        );
    }

    @DeleteMapping("/{id}")
    public String deleteEntry(@PathVariable Long id) {

        ledgerEntryService.deleteEntry(id);

        return "Ledger entry deleted successfully";
    }

    @GetMapping("/income")
    public List<LedgerEntry> getIncomeEntries() {
        return ledgerEntryService.getIncomeEntries();
    }

    @GetMapping("/expense")
    public List<LedgerEntry> getExpenseEntries() {
        return ledgerEntryService.getExpenseEntries();
    }
}