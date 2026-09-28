package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.waterquality.service.ProfitLossService;

@Controller
public class ProfitLossWebController {

    private final ProfitLossService profitLossService;

    public ProfitLossWebController(
            ProfitLossService profitLossService) {

        this.profitLossService = profitLossService;
    }

    @GetMapping("/reports/profit-loss-legacy")
    public String showProfitLoss(Model model) {

        ProfitLossService.ProfitLossData report =
                profitLossService.generateReport();

        model.addAttribute(
                "income",
                report.getIncome()
        );

        model.addAttribute(
                "expense",
                report.getExpense()
        );

        model.addAttribute(
                "netProfit",
                report.getNetProfit()
        );

        return "profit-loss";
    }
}