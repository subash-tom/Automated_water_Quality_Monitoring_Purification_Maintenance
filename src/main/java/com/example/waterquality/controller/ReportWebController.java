package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.waterquality.service.ReportService;

@Controller
@RequestMapping("/reports")
public class ReportWebController {

    private final ReportService reportService;

    public ReportWebController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public String reports() {
        return "reports";
    }

    @GetMapping("/balance-sheet")
    public String balanceSheet(Model model) {

        model.addAllAttributes(
                reportService.getBalanceSheet()
        );

        return "balance-sheet";
    }

    @GetMapping("/profit-loss")
    public String profitLoss(Model model) {

        model.addAllAttributes(
                reportService.getProfitLoss()
        );

        return "profit-loss";
    }

    @GetMapping("/budget-report")
    public String budgetReport(Model model) {

        model.addAttribute(
                "budgets",
                reportService.getBudgetReport()
        );

        return "budget-report";
    }
}