package ru.interview.service;

import org.springframework.transaction.annotation.Transactional;
import ru.interview.model.Question;
import ru.interview.model.User;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    /**
     * Сохранить вопрос
     */
    @Transactional
    void save(Question question);

    /**
     * Найти пользователя по id
     */
    Optional<Question> findById(long id);

    /**
     * Найти всех пользователей
     */
    List<Question> findAll();

    List<Question> saveAll(List<Question> list);
}
