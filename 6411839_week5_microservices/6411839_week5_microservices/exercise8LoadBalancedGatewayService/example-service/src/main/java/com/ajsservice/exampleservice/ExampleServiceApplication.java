package com.ajsservice.exampleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;   // ← use jakarta.servlet, not javax

@SpringBootApplication
@RestController
public class ExampleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleServiceApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest req) {
        return "Hello from example‑service on port " + req.getLocalPort();
    }
}
