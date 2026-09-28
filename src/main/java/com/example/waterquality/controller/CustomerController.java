package com.example.waterquality.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.waterquality.model.Customer;
import com.example.waterquality.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(
            CustomerService customerService) {

        this.customerService = customerService;
    }

    // =========================================
    // GET ALL CUSTOMERS
    // GET /api/customers
    // =========================================

    @GetMapping
    public List<Customer> getCustomers() {

        return customerService.getAllCustomers();
    }

    // =========================================
    // GET CUSTOMER BY ID
    // GET /api/customers/{id}
    // =========================================

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(
            @PathVariable Long id) {

        try {

            Customer customer =
                    customerService.getCustomerById(id);

            return ResponseEntity.ok(customer);

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    // =========================================
    // CREATE CUSTOMER
    // POST /api/customers
    // =========================================

    @PostMapping
    public Customer createCustomer(
            @RequestBody Customer customer) {

        return customerService.createCustomer(
                customer
        );
    }

    // =========================================
    // UPDATE CUSTOMER
    // PUT /api/customers/{id}
    // =========================================

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        try {

            Customer updatedCustomer =
                    customerService.updateCustomer(
                            id,
                            customer
                    );

            return ResponseEntity.ok(
                    updatedCustomer
            );

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    // =========================================
    // DELETE CUSTOMER
    // DELETE /api/customers/{id}
    // =========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long id) {

        try {

            customerService.deleteCustomer(id);

            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }
}