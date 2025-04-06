package com.haruhan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HaruHanApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaruHanApplication.class, args);
    }

}
