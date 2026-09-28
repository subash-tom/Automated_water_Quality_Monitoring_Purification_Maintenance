package com.example.waterquality.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.Account;
import com.example.waterquality.model.Budget;
import com.example.waterquality.model.LedgerEntry;
import com.example.waterquality.repository.AccountRepository;
import com.example.waterquality.repository.BudgetRepository;
import com.example.waterquality.repository.LedgerEntryRepository;

@Service
public class ReportService {

    private final AccountRepository accountRepository;
    private final LedgerEntryRepository ledgerRepository;
    private final BudgetRepository budgetRepository;

    public ReportService(
            AccountRepository accountRepository,
            LedgerEntryRepository ledgerRepository,
            BudgetRepository budgetRepository) {

        this.accountRepository = accountRepository;
        this.ledgerRepository = ledgerRepository;
        this.budgetRepository = budgetRepository;
    }

    // ==========================================
    // BALANCE SHEET
    // ==========================================

    public Map<String, Object> getBalanceSheet() {

        List<Account> accounts = accountRepository.findAll();

        double assets = 0.0;
        double liabilities = 0.0;

        for (Account account : accounts) {

            String type = account.getAccountType();

            if (type == null) {
                continue;
            }

            if (type.equalsIgnoreCase("ASSET")
                    || type.equalsIgnoreCase("ASSETS")) {

                assets++;
            }

            if (type.equalsIgnoreCase("LIABILITY")
                    || type.equalsIgnoreCase("LIABILITIES")) {

                liabilities++;
            }
        }

        Map<String, Object> result = new HashMap<>();

        result.put("assets", assets);
        result.put("liabilities", liabilities);
        result.put("equity", assets - liabilities);

        return result;
    }


    // ==========================================
    // PROFIT & LOSS
    // ==========================================

    public Map<String, Object> getProfitLoss() {

        List<LedgerEntry> entries =
                ledgerRepository.findAll();

        double income = 0.0;
        double expenses = 0.0;

        for (LedgerEntry entry : entries) {

            if (entry.getType() == null) {
                continue;
            }

            if (entry.getType().equalsIgnoreCase("INCOME")
                    || entry.getType().equalsIgnoreCase("REVENUE")) {

                income += entry.getAmount();
            }

            if (entry.getType().equalsIgnoreCase("EXPENSE")
                    || entry.getType().equalsIgnoreCase("EXPENSES")) {

                expenses += entry.getAmount();
            }
        }

        double profit = income - expenses;

        Map<String, Object> result = new HashMap<>();

        result.put("income", income);
        result.put("expenses", expenses);
        result.put("profit", profit);

        return result;
    }


    // ==========================================
    // BUDGET REPORT
    // ==========================================

    public List<Budget> getBudgetReport() {

        return budgetRepository.findAll();
    }
}