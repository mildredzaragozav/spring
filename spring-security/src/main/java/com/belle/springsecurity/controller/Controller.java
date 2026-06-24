package com.belle.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/open")
    public String getOpen() {
        return "OPEN";
    }

    @GetMapping("/closed")
    public String getClosed() {
        return "CLOSED";
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
