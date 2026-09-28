package com.example.waterquality.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.waterquality.model.Product;
import com.example.waterquality.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
    }

    public Product updateProduct(Long id, Product updatedProduct) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setType(updatedProduct.getType());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }

        productRepository.deleteById(id);
    }
}