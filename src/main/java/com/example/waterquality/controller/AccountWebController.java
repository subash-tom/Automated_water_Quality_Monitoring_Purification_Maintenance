package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.waterquality.model.Account;
import com.example.waterquality.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountWebController {

    private final AccountService accountService;

    public AccountWebController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String accounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts";
    }

    @GetMapping("/add")
    public String addAccount(Model model) {
        model.addAttribute("account", new Account());
        return "account-form";
    }

    @PostMapping("/save")
    public String saveAccount(
            @RequestParam String accountCode,
            @RequestParam String accountName,
            @RequestParam String accountType,
            @RequestParam(required = false) String description) {

        accountService.createAccount(
                accountCode,
                accountName,
                accountType,
                description
        );

        return "redirect:/accounts";
    }

    @GetMapping("/edit/{id}")
    public String editAccount(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "account",
                accountService.getAccountById(id)
        );

        return "account-edit";
    }

    @PostMapping("/update/{id}")
    public String updateAccount(
            @PathVariable Long id,
            @RequestParam String accountCode,
            @RequestParam String accountName,
            @RequestParam String accountType,
            @RequestParam(required = false) String description) {

        accountService.updateAccount(
                id,
                accountCode,
                accountName,
                accountType,
                description
        );

        return "redirect:/accounts";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }
}