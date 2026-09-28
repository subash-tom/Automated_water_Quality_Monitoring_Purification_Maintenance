package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.waterquality.model.PurchaseOrder;
import com.example.waterquality.service.PurchaseOrderService;
import com.example.waterquality.service.VendorService;

@Controller
@RequestMapping("/purchase-orders")
public class PurchaseOrderWebController {

    private final PurchaseOrderService purchaseOrderService;
    private final VendorService vendorService;

    public PurchaseOrderWebController(
            PurchaseOrderService purchaseOrderService,
            VendorService vendorService) {

        this.purchaseOrderService = purchaseOrderService;
        this.vendorService = vendorService;
    }

    // ==========================================
    // LIST
    // ==========================================

    @GetMapping
    public String purchaseOrders(Model model) {

        model.addAttribute(
                "purchaseOrders",
                purchaseOrderService.getAllPurchaseOrders()
        );

        return "purchase-orders";
    }

    // ==========================================
    // ADD PAGE
    // ==========================================

    @GetMapping("/add")
    public String addPurchaseOrder(Model model) {

        PurchaseOrder purchaseOrder =
                new PurchaseOrder();

        purchaseOrder.setStatus("PENDING");

        model.addAttribute(
                "purchaseOrder",
                purchaseOrder
        );

        model.addAttribute(
                "vendors",
                vendorService.getAllVendors()
        );

        return "purchase-order-form";
    }

    // ==========================================
    // SAVE
    // ==========================================

    @PostMapping("/save")
    public String savePurchaseOrder(
            @ModelAttribute("purchaseOrder")
            PurchaseOrder purchaseOrder,
            Model model) {

        try {

            purchaseOrderService
                    .createPurchaseOrder(
                            purchaseOrder
                    );

            return "redirect:/purchase-orders";

        } catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "vendors",
                    vendorService.getAllVendors()
            );

            return "purchase-order-form";
        }
    }

    // ==========================================
    // EDIT PAGE
    // ==========================================

    @GetMapping("/edit/{id}")
    public String editPurchaseOrder(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "purchaseOrder",
                purchaseOrderService
                        .getPurchaseOrderById(id)
        );

        model.addAttribute(
                "vendors",
                vendorService.getAllVendors()
        );

        return "purchase-order-form";
    }

    // ==========================================
    // UPDATE
    // ==========================================

    @PostMapping("/update/{id}")
    public String updatePurchaseOrder(
            @PathVariable Long id,
            @ModelAttribute("purchaseOrder")
            PurchaseOrder purchaseOrder,
            Model model) {

        try {

            purchaseOrderService
                    .updatePurchaseOrder(
                            id,
                            purchaseOrder
                    );

            return "redirect:/purchase-orders";

        } catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "vendors",
                    vendorService.getAllVendors()
            );

            return "purchase-order-form";
        }
    }

    // ==========================================
    // DELETE
    // ==========================================

    @GetMapping("/delete/{id}")
    public String deletePurchaseOrder(
            @PathVariable Long id) {

        purchaseOrderService
                .deletePurchaseOrder(id);

        return "redirect:/purchase-orders";
    }
}