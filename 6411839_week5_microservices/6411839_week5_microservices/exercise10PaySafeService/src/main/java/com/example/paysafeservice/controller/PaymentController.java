package com.example.paysafeservice.controller;
import com.example.paysafeservice.service.PaymentProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentProcessingService paymentService;

    @GetMapping("/make")
    public String makePayment() {
        return paymentService.makePayment();
    }
}
