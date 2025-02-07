package com.shabushabu.javashop.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JavaShopApp {

    public static void main(String[] args) {
        SpringApplication.run(JavaShopApp.class, args);
    }

    @Bean(name = "stdRestTemplate")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
