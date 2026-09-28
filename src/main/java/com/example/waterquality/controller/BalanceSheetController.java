package com.example.waterquality.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.Account;
import com.example.waterquality.repository.AccountRepository;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class BalanceSheetController {

    private final AccountRepository accountRepository;

    public BalanceSheetController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/balance-sheet")
    public List<Account> getBalanceSheet() {
        return accountRepository.findAll();
    }
}