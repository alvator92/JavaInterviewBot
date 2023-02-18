package ru.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.interview.model.Question;
import ru.interview.service.telegramm.ExecutionService;

@Component
public class FindQuestionService {

    @Autowired
    private QuestionController controller;
    @Autowired
    private ExecutionService executionService;

    public void findQuestionByName(long chatId, String questName) {
        Question question = controller.findAnswer(questName);
        executionService.prepareAndSendMessage(chatId, question.getAnswer());
    }


}
