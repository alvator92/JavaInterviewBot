package ru.interview.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.interview.model.Status;
import ru.interview.repository.StatusRepository;

import java.util.NoSuchElementException;

@Slf4j
public class StatusServiceImpl implements StatusService{

    @Autowired
    private StatusRepository repository;

    @Override
    public void save(Status status) {
        repository.save(status);
    }

    @Override
    public Status getQuestion(Long chatId) {
        try {
            Status status = repository.findQuestionInActiveStatus(chatId).get();
            return status;
        } catch (NoSuchElementException e) {
            log.error("Нет Заданных вопросов : " + e.toString());
        }
        return null;
    }

    @Override
    public void closeStatus(Long chatId) {
        repository.closeStatus(chatId);
    }
}
