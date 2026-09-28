package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.waterquality.model.Vendor;
import com.example.waterquality.service.VendorService;

@Controller
@RequestMapping("/vendors")
public class VendorWebController {

    private final VendorService vendorService;

    public VendorWebController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // =========================================
    // VENDOR LIST
    // GET /vendors
    // =========================================

    @GetMapping
    public String vendors(Model model) {

        model.addAttribute(
                "vendors",
                vendorService.getAllVendors()
        );

        return "vendors";
    }

    // =========================================
    // ADD VENDOR PAGE
    // GET /vendors/add
    // =========================================

    @GetMapping("/add")
    public String addVendor(Model model) {

        model.addAttribute(
                "vendor",
                new Vendor()
        );

        return "vendor-form";
    }

    // =========================================
    // SAVE VENDOR
    // POST /vendors/save
    // =========================================

    @PostMapping("/save")
    public String saveVendor(
            @ModelAttribute("vendor") Vendor vendor) {

        vendorService.createVendor(vendor);

        return "redirect:/vendors";
    }

    // =========================================
    // EDIT VENDOR
    // GET /vendors/edit/{id}
    // =========================================

    @GetMapping("/edit/{id}")
    public String editVendor(
            @PathVariable Long id,
            Model model) {

        Vendor vendor =
                vendorService.getVendorById(id);

        model.addAttribute(
                "vendor",
                vendor
        );

        return "vendor-form";
    }

    // =========================================
    // UPDATE VENDOR
    // POST /vendors/update/{id}
    // =========================================

    @PostMapping("/update/{id}")
    public String updateVendor(
            @PathVariable Long id,
            @ModelAttribute("vendor") Vendor vendor) {

        vendorService.updateVendor(id, vendor);

        return "redirect:/vendors";
    }

    // =========================================
    // DELETE VENDOR
    // GET /vendors/delete/{id}
    // =========================================

    @GetMapping("/delete/{id}")
    public String deleteVendor(
            @PathVariable Long id) {

        vendorService.deleteVendor(id);

        return "redirect:/vendors";
    }
}