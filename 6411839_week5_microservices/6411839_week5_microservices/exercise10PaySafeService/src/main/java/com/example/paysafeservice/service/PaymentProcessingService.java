package com.example.paysafeservice.service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessingService {

    @Autowired
    private SlowBankApiService slowBankApiService;

    @CircuitBreaker(name = "bankApiCB", fallbackMethod = "bankApiFallback")
    public String makePayment() {
        return slowBankApiService.callBankApi();
    }

    // Fallback method (matches original method's parameters + Throwable)
    public String bankApiFallback(Throwable t) {
        System.err.println("Fallback triggered due to: " + t.getMessage());
        return "Payment service is temporarily unavailable. Please try again later.";
    }
}
