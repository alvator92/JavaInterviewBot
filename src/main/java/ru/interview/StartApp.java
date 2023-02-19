package ru.interview;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.interview.model.Status;

@SpringBootApplication
@Slf4j
public class StartApp {
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
        System.out.println("Hello JavaJunior!");
        Status status = new Status.Builder()
                .withChatId(1234L)
                .withQuestion("Sad")
                .withStatus("ACTIVE")
                .build();
    }
}
