package com.c2.hospital.equipmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableEurekaClient
@EnableHystrix
@SpringBootApplication
public class EquipmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentServiceApplication.class, args);
    }

}
