package com.example.waterquality.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.Budget;
import com.example.waterquality.service.BudgetService;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    // ==========================================
    // GET ALL BUDGETS
    // GET /api/budgets
    // ==========================================

    @GetMapping
    public List<Budget> getAllBudgets() {

        return budgetService.getAllBudgets();
    }

    // ==========================================
    // GET BUDGET BY ID
    // GET /api/budgets/{id}
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(
            @PathVariable Long id) {

        try {

            Budget budget =
                    budgetService.getBudgetById(id);

            return ResponseEntity.ok(budget);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    // ==========================================
    // CREATE BUDGET
    // POST /api/budgets
    // ==========================================

    @PostMapping
    public ResponseEntity<Budget> createBudget(
            @RequestBody Budget budget) {

        Budget savedBudget =
                budgetService.createBudget(budget);

        return ResponseEntity.ok(savedBudget);
    }

    // ==========================================
    // UPDATE BUDGET
    // PUT /api/budgets/{id}
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(
            @PathVariable Long id,
            @RequestBody Budget budget) {

        try {

            Budget updatedBudget =
                    budgetService.updateBudget(
                            id,
                            budget
                    );

            return ResponseEntity.ok(updatedBudget);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    // ==========================================
    // DELETE BUDGET
    // DELETE /api/budgets/{id}
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(
            @PathVariable Long id) {

        try {

            budgetService.deleteBudget(id);

            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }
}