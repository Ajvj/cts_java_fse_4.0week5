package com.ajsservice.resilience.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.circuitbreaker.resilience4j.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import io.github.resilience4j.circuitbreaker.*;
import io.github.resilience4j.timelimiter.*;

@Configuration
public class ResilienceConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "defaultCustomizer")
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id ->
                new Resilience4JConfigBuilder(id)
                        .circuitBreakerConfig(CircuitBreakerConfig.custom()
                                .slidingWindowSize(10)
                                .failureRateThreshold(50)
                                .build())
                        .timeLimiterConfig(TimeLimiterConfig.ofDefaults())
                        .build());
    }
}
