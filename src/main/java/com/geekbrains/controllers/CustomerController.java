package com.geekbrains.controllers;

import com.geekbrains.model.Product;
import com.geekbrains.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/add")
    public String showAddForm() {
        return "product_add_form";
    }

//    @PostMapping("/add")
//    public String addProduct(
//            @RequestParam(name = "id", required = false) Long id,
//            @RequestParam String title,
//            @RequestParam String description,
//            @RequestParam String brand,
//            @RequestParam Double price
//    ) {
//        Product product = new Product(id, title, description, brand, price);
//        cartService.saveOrUpdate(product);
//        return "redirect:/products/";
//    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product newProduct){
        cartService.saveOrUpdate(newProduct);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id, Model model
    ) {
        model.addAttribute("product", cartService.findById(id));
        return "product_edit_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(
            @ModelAttribute Product modifiedProduct
    ) {
        cartService.saveOrUpdate(modifiedProduct);
        return "redirect:/products/";
    }

    @GetMapping("/product/{id}")
    public String ырщцЗкщвгсе(
            @PathVariable Long id, Model model
    ) {
        model.addAttribute("product", cartService.findById(id));
        return "product";
    }

//    @GetMapping(value = "/json/{id}")
//    @ResponseBody
//    public Product showJsonCustomer(
//            @PathVariable Long id
//    ) {
//
//        return cartService.findById(id);
//    }

    @GetMapping(value = "/json/{id}")
    public HttpEntity showJsonCustomer(
            @PathVariable Long id
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> entity = new HttpEntity<>(cartService.findById(id), headers);
        return entity;
    }
}
