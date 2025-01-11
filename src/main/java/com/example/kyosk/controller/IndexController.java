package com.example.kyosk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "Welcome to Kyosk API!";
    }

    @GetMapping("/health")
    public String health() {
        return "Up and running!";
    }
}
