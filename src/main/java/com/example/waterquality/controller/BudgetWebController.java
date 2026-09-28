package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.waterquality.model.Budget;
import com.example.waterquality.service.BudgetService;

@Controller
@RequestMapping("/budgets")
public class BudgetWebController {

    private final BudgetService budgetService;

    public BudgetWebController(
            BudgetService budgetService) {

        this.budgetService = budgetService;
    }

    // ==========================================
    // SHOW ALL BUDGETS
    // URL: /budgets
    // ==========================================

    @GetMapping
    public String budgets(Model model) {

        model.addAttribute(
                "budgets",
                budgetService.getAllBudgets()
        );

        return "budgets";
    }

    // ==========================================
    // SHOW ADD FORM
    // URL: /budgets/new
    // ==========================================

    @GetMapping("/new")
    public String newBudget(Model model) {

        Budget budget = new Budget();

        // Default values
        budget.setBudgetAmount(0.0);
        budget.setSpentAmount(0.0);
        budget.setRemainingAmount(0.0);
        budget.setStatus("ACTIVE");

        model.addAttribute(
                "budget",
                budget
        );

        return "budget-form";
    }

    // ==========================================
    // SAVE NEW BUDGET
    // URL: /budgets/save
    // ==========================================

    @PostMapping("/save")
    public String saveBudget(
            @ModelAttribute("budget")
            Budget budget) {

        // Default budget amount
        if (budget.getBudgetAmount() == null) {

            budget.setBudgetAmount(0.0);
        }

        // Default spent amount
        if (budget.getSpentAmount() == null) {

            budget.setSpentAmount(0.0);
        }

        // Calculate remaining amount
        double budgetAmount =
                budget.getBudgetAmount();

        double spentAmount =
                budget.getSpentAmount();

        double remaining =
                budgetAmount - spentAmount;

        budget.setRemainingAmount(
                remaining
        );

        // Set status
        if (remaining < 0) {

            budget.setStatus("OVER_BUDGET");

        } else if (remaining == 0) {

            budget.setStatus("FULLY_USED");

        } else {

            budget.setStatus("ACTIVE");
        }

        budgetService.createBudget(budget);

        return "redirect:/budgets";
    }

    // ==========================================
    // SHOW EDIT FORM
    // URL: /budgets/edit/{id}
    // ==========================================

    @GetMapping("/edit/{id}")
    public String editBudget(
            @PathVariable Long id,
            Model model) {

        Budget budget =
                budgetService.getBudgetById(id);

        model.addAttribute(
                "budget",
                budget
        );

        return "budget-form";
    }

    // ==========================================
    // UPDATE BUDGET
    // URL: /budgets/update/{id}
    // ==========================================

    @PostMapping("/update/{id}")
    public String updateBudget(
            @PathVariable Long id,
            @ModelAttribute("budget")
            Budget budget) {

        if (budget.getBudgetAmount() == null) {

            budget.setBudgetAmount(0.0);
        }

        if (budget.getSpentAmount() == null) {

            budget.setSpentAmount(0.0);
        }

        double remaining =
                budget.getBudgetAmount()
                        - budget.getSpentAmount();

        budget.setRemainingAmount(
                remaining
        );

        if (remaining < 0) {

            budget.setStatus("OVER_BUDGET");

        } else if (remaining == 0) {

            budget.setStatus("FULLY_USED");

        } else {

            budget.setStatus("ACTIVE");
        }

        budgetService.updateBudget(
                id,
                budget
        );

        return "redirect:/budgets";
    }

    // ==========================================
    // DELETE BUDGET
    // URL: /budgets/delete/{id}
    // ==========================================

    @GetMapping("/delete/{id}")
    public String deleteBudget(
            @PathVariable Long id) {

        budgetService.deleteBudget(id);

        return "redirect:/budgets";
    }
}