package com.example.waterquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.waterquality.model.Product;
import com.example.waterquality.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductWebController {

    private final ProductService productService;

    public ProductWebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productsPage(Model model) {

        model.addAttribute(
                "products",
                productService.getAllProducts()
        );

        return "products";
    }

    @GetMapping("/add")
    public String addProductPage(Model model) {

        model.addAttribute(
                "product",
                new Product()
        );

        return "product-form";
    }

    @PostMapping("/save")
    public String saveProduct(
            @ModelAttribute Product product) {

        productService.addProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "product",
                productService.getProductById(id)
        );

        return "product-form";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute Product product) {

        productService.updateProduct(id, product);

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return "redirect:/products";
    }
}