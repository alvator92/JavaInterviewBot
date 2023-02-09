package ru.interview.service;

import org.springframework.transaction.annotation.Transactional;
import ru.interview.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Сохранить нового пользователя
     */
    @Transactional
    void save(User user);

    /**
     * Найти пользователя по chatId
     */
    Optional<User> findById(long id);

    /**
     * Найти всех пользователей
     */
    List<User> findAll();
}
