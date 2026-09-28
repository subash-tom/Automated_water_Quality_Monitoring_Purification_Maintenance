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

import com.example.waterquality.model.Account;
import com.example.waterquality.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // GET ALL
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // GET ONE
    @GetMapping("/{id}")
    public Account getAccount(
            @PathVariable Long id) {

        return accountService.getAccountById(id);
    }

    // CREATE
    @PostMapping
    public Account createAccount(
            @RequestParam String accountCode,
            @RequestParam String accountName,
            @RequestParam String accountType,
            @RequestParam(required = false) String description) {

        return accountService.createAccount(
                accountCode,
                accountName,
                accountType,
                description
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public Account updateAccount(
            @PathVariable Long id,
            @RequestParam String accountCode,
            @RequestParam String accountName,
            @RequestParam String accountType,
            @RequestParam(required = false) String description) {

        return accountService.updateAccount(
                id,
                accountCode,
                accountName,
                accountType,
                description
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteAccount(
            @PathVariable Long id) {

        accountService.deleteAccount(id);

        return "Account deleted successfully";
    }
}