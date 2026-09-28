package com.example.waterquality.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.Budget;
import com.example.waterquality.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    // ==========================================
    // PROFIT & LOSS
    // ==========================================

    @GetMapping("/profit-loss")
    public Map<String, Object> profitLoss() {

        return reportService.getProfitLoss();
    }


    // ==========================================
    // BUDGET REPORT
    // ==========================================

    @GetMapping("/budget")
    public List<Budget> budgetReport() {

        return reportService.getBudgetReport();
    }
}