package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.waterquality.repository.AccountRepository;

@Controller
@RequestMapping("/reports")
public class BalanceSheetWebController {

    private final AccountRepository accountRepository;

    public BalanceSheetWebController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/balance-sheet-legacy")
    public String balanceSheet(Model model) {

        model.addAttribute(
                "accounts",
                accountRepository.findAll()
        );

        return "balance-sheet";
    }
}