package com.geekbrains.controllers;

import com.geekbrains.model.Product;
import com.geekbrains.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class CustomerController {

    private CartService cartService;

    @Autowired
    public CustomerController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String showAll(Model model) {
        List<Product> products = cartService.getProductList();
        model.addAttribute("products", products);

        // WEB-INF/templates/products.html
        return "products";
    }
}
