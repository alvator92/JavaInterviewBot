package ru.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.interview.configuration.JpaBean;
import ru.interview.enums.SectionsLinks;
import ru.interview.model.Question;
import ru.interview.service.parsing.ParsingListOfQuestions;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionController {
    @Autowired
    private JpaBean config;
    private List<Question> list;

    public List<Question> saveAllQuestions() {
        list = new ArrayList<>();

        for (SectionsLinks value : SectionsLinks.values()) {
            ParsingListOfQuestions listOfQuestions =
                    new ParsingListOfQuestions();
            list.addAll(listOfQuestions.getQuestion(value.getName(), value.getUrl()));

        }

        config.questionService().saveAll(list);

        return list;
    }
}
