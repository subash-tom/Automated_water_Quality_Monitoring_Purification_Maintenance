package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.waterquality.service.CustomerService;
import com.example.waterquality.service.ProductService;
import com.example.waterquality.service.SalesOrderService;

@Controller
@RequestMapping("/sales-orders")
public class SalesOrderWebController {

    private final SalesOrderService salesOrderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public SalesOrderWebController(
            SalesOrderService salesOrderService,
            CustomerService customerService,
            ProductService productService) {

        this.salesOrderService = salesOrderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping
    public String orders(Model model) {

        model.addAttribute(
                "orders",
                salesOrderService.getAllOrders()
        );

        return "sales-orders";
    }

    @GetMapping("/add")
    public String addOrderPage(Model model) {

        model.addAttribute(
                "customers",
                customerService.getAllCustomers()
        );

        model.addAttribute(
                "products",
                productService.getAllProducts()
        );

        return "sales-order-form";
    }

    @PostMapping("/save")
    public String saveOrder(
            @RequestParam Long customerId,
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            @RequestParam Double unitPrice,
            @RequestParam String orderDate,
            @RequestParam String status) {

        salesOrderService.createOrder(
                customerId,
                productId,
                quantity,
                unitPrice,
                orderDate,
                status
        );

        return "redirect:/sales-orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {

        salesOrderService.deleteOrder(id);

        return "redirect:/sales-orders";
    }
}