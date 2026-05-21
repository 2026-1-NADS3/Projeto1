package com.maya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MayaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MayaApplication.class, args);
    }

    @GetMapping("/")
    public String status() {
        return "API Maya RPG: Infraestrutura Cloud Native 100% Validada!";
    }
}
