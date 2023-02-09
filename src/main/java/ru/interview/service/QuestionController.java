package ru.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.interview.configuration.JpaBean;

@Component
public class QuestionController {
    @Autowired
    private JpaBean config;

    public String saveNewQuest() {
        return "response";
    }
}
