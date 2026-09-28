package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.waterquality.model.Customer;
import com.example.waterquality.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerWebController {

    private final CustomerService customerService;

    public CustomerWebController(
            CustomerService customerService) {

        this.customerService = customerService;
    }

    // =========================================
    // CUSTOMER LIST
    // GET /customers
    // =========================================

    @GetMapping
    public String customers(Model model) {

        model.addAttribute(
                "customers",
                customerService.getAllCustomers()
        );

        return "customers";
    }

    // =========================================
    // ADD CUSTOMER PAGE
    // GET /customers/add
    // =========================================

    @GetMapping("/add")
    public String addCustomer(Model model) {

        model.addAttribute(
                "customer",
                new Customer()
        );

        return "customer-form";
    }

    // =========================================
    // SAVE CUSTOMER
    // POST /customers/save
    // =========================================

    @PostMapping("/save")
    public String saveCustomer(
            @ModelAttribute("customer")
            Customer customer) {

        customerService.createCustomer(
                customer
        );

        return "redirect:/customers";
    }

    // =========================================
    // EDIT CUSTOMER
    // GET /customers/edit/{id}
    // =========================================

    @GetMapping("/edit/{id}")
    public String editCustomer(
            @PathVariable Long id,
            Model model) {

        Customer customer =
                customerService.getCustomerById(id);

        model.addAttribute(
                "customer",
                customer
        );

        return "customer-form";
    }

    // =========================================
    // UPDATE CUSTOMER
    // POST /customers/update/{id}
    // =========================================

    @PostMapping("/update/{id}")
    public String updateCustomer(
            @PathVariable Long id,
            @ModelAttribute("customer")
            Customer customer) {

        customerService.updateCustomer(
                id,
                customer
        );

        return "redirect:/customers";
    }

    // =========================================
    // DELETE CUSTOMER
    // GET /customers/delete/{id}
    // =========================================

    @GetMapping("/delete/{id}")
    public String deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteCustomer(id);

        return "redirect:/customers";
    }
}