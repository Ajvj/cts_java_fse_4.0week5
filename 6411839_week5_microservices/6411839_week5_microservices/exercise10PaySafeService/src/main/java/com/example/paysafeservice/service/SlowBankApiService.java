package com.example.paysafeservice.service;
import org.springframework.stereotype.Service;

@Service
public class SlowBankApiService {

    public String callBankApi() {
        try {
            Thread.sleep(5000); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted", e);
        }
        // Randomly fail to simulate unreliable third-party API
        if (Math.random() > 0.5) {
            throw new RuntimeException("Bank API timeout!");
        }
        return "Bank payment success!";
    }
}
