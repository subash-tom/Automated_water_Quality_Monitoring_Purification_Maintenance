package com.example.waterquality.controller;

import com.example.waterquality.service.LedgerEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ledger")
public class LedgerEntryWebController {

    private final LedgerEntryService ledgerEntryService;

    public LedgerEntryWebController(
            LedgerEntryService ledgerEntryService) {
        this.ledgerEntryService = ledgerEntryService;
    }

    @GetMapping
    public String ledger(Model model) {

        model.addAttribute(
                "entries",
                ledgerEntryService.getAllEntries()
        );

        return "ledger";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        return "journal-entry-form";
    }

    @PostMapping("/save")
    public String saveEntry(
            @RequestParam String type,
            @RequestParam String category,
            @RequestParam Double amount,
            @RequestParam String description) {

        ledgerEntryService.createEntry(
                type,
                category,
                amount,
                description
        );

        return "redirect:/ledger";
    }

    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "entry",
                ledgerEntryService.getEntryById(id)
        );

        return "journal-entry-edit";
    }

    @PostMapping("/update/{id}")
    public String updateEntry(
            @PathVariable Long id,
            @RequestParam String type,
            @RequestParam String category,
            @RequestParam Double amount,
            @RequestParam String description) {

        ledgerEntryService.updateEntry(
                id,
                type,
                category,
                amount,
                description
        );

        return "redirect:/ledger";
    }

    @GetMapping("/delete/{id}")
    public String deleteEntry(@PathVariable Long id) {

        ledgerEntryService.deleteEntry(id);

        return "redirect:/ledger";
    }
}