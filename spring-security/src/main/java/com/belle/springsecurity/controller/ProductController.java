package com.belle.springsecurity.controller;

import com.belle.springsecurity.dto.ProductRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getProducts() {
        return "A list of products";
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRequest product) {
        return "New product added with details: "
                + product.toString();
    }

}
