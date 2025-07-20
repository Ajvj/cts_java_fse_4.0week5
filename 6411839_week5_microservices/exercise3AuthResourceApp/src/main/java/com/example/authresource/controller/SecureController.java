package com.example.authresource.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure")
    public String secure() {
        return "This is a secure endpoint";
    }

    @GetMapping("/public/hello")
    public String hello() {
        return "Hello, world! (no auth required)";
    }
}
