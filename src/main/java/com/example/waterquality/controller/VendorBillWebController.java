package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.waterquality.model.VendorBill;
import com.example.waterquality.service.PurchaseOrderService;
import com.example.waterquality.service.VendorBillService;

@Controller
@RequestMapping("/vendor-bills")
public class VendorBillWebController {

    private final VendorBillService vendorBillService;
    private final PurchaseOrderService purchaseOrderService;

    public VendorBillWebController(
            VendorBillService vendorBillService,
            PurchaseOrderService purchaseOrderService) {

        this.vendorBillService = vendorBillService;
        this.purchaseOrderService = purchaseOrderService;
    }

    // =========================
    // LIST
    // =========================

    @GetMapping
    public String vendorBills(Model model) {

        model.addAttribute(
                "bills",
                vendorBillService.getAllBills()
        );

        return "vendor-bills";
    }

    // =========================
    // ADD
    // =========================

    @GetMapping("/add")
    public String addBill(Model model) {

        model.addAttribute(
                "bill",
                new VendorBill()
        );

        model.addAttribute(
                "purchaseOrders",
                purchaseOrderService
                        .getAllPurchaseOrders()
        );

        return "vendor-bill-form";
    }

    // =========================
    // SAVE
    // =========================

    @PostMapping("/save")
    public String saveBill(
            @RequestParam Long purchaseOrderId,
            @RequestParam String billNumber,
            @RequestParam String billDate,
            @RequestParam(required = false)
            String status) {

        vendorBillService.createBill(
                purchaseOrderId,
                billNumber,
                billDate,
                status
        );

        return "redirect:/vendor-bills";
    }

    // =========================
    // DELETE
    // =========================

    @GetMapping("/delete/{id}")
    public String deleteBill(
            @PathVariable Long id) {

        vendorBillService.deleteBill(id);

        return "redirect:/vendor-bills";
    }
}