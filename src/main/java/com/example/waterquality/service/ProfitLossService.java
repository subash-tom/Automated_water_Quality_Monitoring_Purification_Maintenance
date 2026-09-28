package com.example.waterquality.service;

import com.example.waterquality.model.LedgerEntry;
import com.example.waterquality.repository.LedgerEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfitLossService {

    private final LedgerEntryRepository ledgerEntryRepository;

    public ProfitLossService(LedgerEntryRepository ledgerEntryRepository) {
        this.ledgerEntryRepository = ledgerEntryRepository;
    }

    public ProfitLossData generateReport() {

        List<LedgerEntry> incomeEntries =
                ledgerEntryRepository.findByType("INCOME");

        List<LedgerEntry> expenseEntries =
                ledgerEntryRepository.findByType("EXPENSE");

        double income = 0.0;
        double expense = 0.0;

        // Calculate total income
        for (LedgerEntry entry : incomeEntries) {

            if (entry.getAmount() != null) {
                income += entry.getAmount();
            }
        }

        // Calculate total expense
        for (LedgerEntry entry : expenseEntries) {

            if (entry.getAmount() != null) {
                expense += entry.getAmount();
            }
        }

        // Profit = Income - Expense
        double netProfit = income - expense;

        return new ProfitLossData(
                income,
                expense,
                netProfit
        );
    }

    public static class ProfitLossData {

        private final double income;
        private final double expense;
        private final double netProfit;

        public ProfitLossData(
                double income,
                double expense,
                double netProfit) {

            this.income = income;
            this.expense = expense;
            this.netProfit = netProfit;
        }

        public double getIncome() {
            return income;
        }

        public double getExpense() {
            return expense;
        }

        public double getNetProfit() {
            return netProfit;
        }
    }
}