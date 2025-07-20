package com.ajsservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EdgeGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdgeGatewayServiceApplication.class, args);
    }

    /**
     * Programmatic route definition: forwards /example/** to httpbin.org,
     * strips the first path segment, and retries twice on failure.
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("example_route", r -> r
                        .path("/example/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .retry(config -> config.setRetries(2))
                        )
                        .uri("https://httpbin.org")
                )
                // add more routes here if needed
                .build();
    }
}
