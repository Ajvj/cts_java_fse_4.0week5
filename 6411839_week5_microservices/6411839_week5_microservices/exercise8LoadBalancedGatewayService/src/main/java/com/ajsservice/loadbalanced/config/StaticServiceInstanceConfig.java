package com.ajsservice.loadbalanced.config;

import java.util.List;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;  // correct package
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import reactor.core.publisher.Flux;

@Configuration
public class StaticServiceInstanceConfig {

    @Bean
    @Primary
    public ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new ServiceInstanceListSupplier() {

            @Override
            public String getServiceId() {
                return "example-service";
            }

            @Override
            public Flux<List<ServiceInstance>> get() {
                // supply two instances on localhost:9001 and 9002
                return Flux.just(List.of(
                        new DefaultServiceInstance(
                                "example-service-1",
                                "example-service",
                                "localhost",
                                9001,
                                false
                        ),
                        new DefaultServiceInstance(
                                "example-service-2",
                                "example-service",
                                "localhost",
                                9002,
                                false
                        )
                ));
            }
        };
    }
}