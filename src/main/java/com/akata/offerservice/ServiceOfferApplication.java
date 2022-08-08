package com.akata.offerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceOfferApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOfferApplication.class, args);
    }

}
