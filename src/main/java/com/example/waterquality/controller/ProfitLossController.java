package com.example.waterquality.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.service.ProfitLossService;

@RestController
@RequestMapping("/api")
public class ProfitLossController {

    private final ProfitLossService profitLossService;

    public ProfitLossController(
            ProfitLossService profitLossService) {

        this.profitLossService = profitLossService;
    }

    @GetMapping("/profit-loss")
    public ProfitLossService.ProfitLossData getProfitLoss() {

        return profitLossService.generateReport();
    }
}