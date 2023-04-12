package ru.finalproject.animal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class AnimalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnimalServiceApplication.class, args);
    }

}
