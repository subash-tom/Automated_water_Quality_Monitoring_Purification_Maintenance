package com.example.waterquality.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.SalesOrder;
import com.example.waterquality.service.SalesOrderService;

@RestController
@RequestMapping("/api/sales-orders")
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    // GET ALL SALES ORDERS
    @GetMapping
    public List<SalesOrder> getAllOrders() {
        return salesOrderService.getAllOrders();
    }

    // GET ONE SALES ORDER
    @GetMapping("/{id}")
    public SalesOrder getOrder(@PathVariable Long id) {
        return salesOrderService.getOrderById(id);
    }

    // CREATE SALES ORDER
    @PostMapping
    public SalesOrder createOrder(
            @RequestParam Long customerId,
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            @RequestParam Double unitPrice,
            @RequestParam String orderDate,
            @RequestParam String status) {

        return salesOrderService.createOrder(
                customerId,
                productId,
                quantity,
                unitPrice,
                orderDate,
                status
        );
    }

    // DELETE SALES ORDER
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {

        salesOrderService.deleteOrder(id);

        return "Sales Order deleted successfully";
    }
}