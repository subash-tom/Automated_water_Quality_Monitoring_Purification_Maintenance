package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.Customer;
import com.example.waterquality.model.Product;
import com.example.waterquality.model.SalesOrder;
import com.example.waterquality.repository.CustomerRepository;
import com.example.waterquality.repository.ProductRepository;
import com.example.waterquality.repository.SalesOrderRepository;

@Service
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public SalesOrderService(
            SalesOrderRepository salesOrderRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository) {

        this.salesOrderRepository = salesOrderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<SalesOrder> getAllOrders() {
        return salesOrderRepository.findAll();
    }

    public SalesOrder getOrderById(Long id) {
        return salesOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales Order not found"));
    }

    public SalesOrder createOrder(
            Long customerId,
            Long productId,
            Integer quantity,
            Double unitPrice,
            String orderDate,
            String status) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        SalesOrder order = new SalesOrder();

        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setUnitPrice(unitPrice);

        double total = quantity * unitPrice;

        order.setTotalAmount(total);
        order.setOrderDate(orderDate);

        if (status == null || status.isBlank()) {
            order.setStatus("PENDING");
        } else {
            order.setStatus(status);
        }

        return salesOrderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        salesOrderRepository.deleteById(id);
    }
}