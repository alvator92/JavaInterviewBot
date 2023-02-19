package ru.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.interview.configuration.JpaBean;
import ru.interview.model.Status;

@Component
public class StatusController {

    @Autowired
    private JpaBean getBean;

    /**
     * Найти вопрос в активном статусе
     */
    public Status findQuestionInActiveStatus(Long chatId) {
        return getBean.statusService().getQuestion(chatId);
    }

    /**
     * Создание записи о том что вопрос был задан и находится в активном статусе
     */
    public void registerQuestion(long chatId, String question) {

        Status status = new Status.Builder()
                .withChatId(chatId)
                .withQuestion(question)
                .withStatus("ACTIVE")
                .build();

        getBean.statusService().save(status);

    }

    /**
     * Закрыть статус
     */
    public void closeActiveStatus(long chatId) {
        getBean.statusService().closeStatus(chatId);
    }

}
