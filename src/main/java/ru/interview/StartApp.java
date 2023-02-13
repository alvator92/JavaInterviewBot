package ru.interview;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.interview.service.parsing.QuestionParseService;

@SpringBootApplication
@Slf4j
public class StartApp {
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
        System.out.println("Hello JavaJunior!");
        QuestionParseService quest = new QuestionParseService();
        log.info(quest.getQuestion());
    }
}
