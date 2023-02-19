package ru.interview.service;

import org.springframework.transaction.annotation.Transactional;
import ru.interview.model.Status;

public interface StatusService {

    @Transactional
    void save(Status status);

    Status getQuestion(Long chatId);

    void closeStatus(Long chatId);

}
