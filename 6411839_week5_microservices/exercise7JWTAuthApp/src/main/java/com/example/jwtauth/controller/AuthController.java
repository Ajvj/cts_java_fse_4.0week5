package com.example.jwtauth.controller;

import com.example.jwtauth.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    private final JwtTokenProvider provider;

    public AuthController(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> creds) {
        String username = creds.get("username");
        String password = creds.get("password");
        // TODO: validate credentials against a user store
        String token = provider.createToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/secure/data")
    public ResponseEntity<String> secureData() {
        return ResponseEntity.ok("Secure data accessed");
    }
}