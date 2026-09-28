package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.Customer;
import com.example.waterquality.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Customer getCustomerById(Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + id
                        )
                );
    }

    // Create customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Update customer
    public Customer updateCustomer(Long id, Customer customer) {

        Customer existingCustomer =
                getCustomerById(id);

        existingCustomer.setName(
                customer.getName()
        );

        existingCustomer.setContactPerson(
                customer.getContactPerson()
        );

        existingCustomer.setPhone(
                customer.getPhone()
        );

        existingCustomer.setEmail(
                customer.getEmail()
        );

        existingCustomer.setAddress(
                customer.getAddress()
        );

        return customerRepository.save(
                existingCustomer
        );
    }

    // Delete customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}