package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.Budget;
import com.example.waterquality.repository.BudgetRepository;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(
            BudgetRepository budgetRepository) {

        this.budgetRepository = budgetRepository;
    }

    // ==========================================
    // GET ALL
    // ==========================================

    public List<Budget> getAllBudgets() {

        return budgetRepository.findAll();
    }

    // ==========================================
    // GET BY ID
    // ==========================================

    public Budget getBudgetById(Long id) {

        return budgetRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Budget not found: " + id));
    }

    // ==========================================
    // CREATE
    // ==========================================

    public Budget createBudget(Budget budget) {

        if (budget.getBudgetName() == null ||
                budget.getBudgetName().trim().isEmpty()) {

            throw new RuntimeException(
                    "Budget name is required");
        }

        if (budget.getBudgetYear() == null) {

            throw new RuntimeException(
                    "Budget year is required");
        }

        if (budget.getBudgetAmount() == null) {

            budget.setBudgetAmount(0.0);
        }

        if (budget.getSpentAmount() == null) {

            budget.setSpentAmount(0.0);
        }

        calculateAmounts(budget);

        return budgetRepository.save(budget);
    }

    // ==========================================
    // UPDATE
    // ==========================================

    public Budget updateBudget(
            Long id,
            Budget updatedBudget) {

        Budget existing =
                getBudgetById(id);

        existing.setBudgetName(
                updatedBudget.getBudgetName());

        existing.setBudgetYear(
                updatedBudget.getBudgetYear());

        existing.setBudgetAmount(
                updatedBudget.getBudgetAmount());

        existing.setSpentAmount(
                updatedBudget.getSpentAmount());

        calculateAmounts(existing);

        return budgetRepository.save(existing);
    }

    // ==========================================
    // CALCULATE
    // ==========================================

    private void calculateAmounts(Budget budget) {

        double total =
                budget.getBudgetAmount() != null
                        ? budget.getBudgetAmount()
                        : 0.0;

        double spent =
                budget.getSpentAmount() != null
                        ? budget.getSpentAmount()
                        : 0.0;

        double remaining = total - spent;

        budget.setRemainingAmount(remaining);

        if (remaining < 0) {

            budget.setStatus("OVER_BUDGET");

        } else if (remaining == 0) {

            budget.setStatus("FULLY_USED");

        } else {

            budget.setStatus("ACTIVE");
        }
    }

    // ==========================================
    // DELETE
    // ==========================================

    public void deleteBudget(Long id) {

        if (!budgetRepository.existsById(id)) {

            throw new RuntimeException(
                    "Budget not found: " + id);
        }

        budgetRepository.deleteById(id);
    }
}