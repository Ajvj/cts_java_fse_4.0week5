package com.ajsservice.resilience.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ResilienceGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResilienceGatewayServiceApplication.class, args);
    }

    // Optional: a simple fallback endpoint
    @GetMapping("/fallback")
    public String fallback() {
        return "Service is currently unavailable. Please try again later.";
    }

    // (If you prefer Java config over properties, you can define your route here.)
    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("resilience_route", r -> r
                        .path("/resilient/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .circuitBreaker(config -> config
                                        .setName("exampleCircuitBreaker")
                                        .setFallbackUri("forward:/fallback")))
                        .uri("http://localhost:9001"))
                .build();
    }
}
