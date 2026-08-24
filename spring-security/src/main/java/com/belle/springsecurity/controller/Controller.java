package com.belle.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/open")
    public String getOpen() {
        return "Access to 'GET /open' granted";
    }

    @PostMapping("/open")
    public String postOpen() {
        return "Access to 'POST /open' granted";
    }

    @GetMapping("/authenticated")
    public String getClosed() {
        return "AUTHENTICATED";
    }

    @GetMapping("/special")
    public String special() {
        return "SPECIAL";
    }

    @GetMapping("/basic")
    public String basic() {
        return "BASIC";
    }
}
