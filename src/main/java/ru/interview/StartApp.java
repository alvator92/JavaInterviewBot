package ru.interview;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class StartApp {
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
        System.out.println("Hello JavaJunior!");

    }
}
