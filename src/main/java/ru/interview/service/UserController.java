package ru.interview.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.interview.configuration.JpaBean;
import ru.interview.model.User;
import ru.interview.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;

@Component
@Slf4j
public class UserController {

    @Autowired
    private JpaBean getBean;
    private UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    /**
     * Проверка пользователя на наличие в БД. При отсутствии такового - создание нового пользователя.
     */
    public void registerUser(Message msg) {
        if (getBean.userService().findById(msg.getChatId()).isEmpty()) {
            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User.Builder()
                    .withChatId(chatId)
                    .withFirstName(chat.getFirstName())
                    .withLastName(chat.getLastName())
                    .withUserName(chat.getUserName())
                    .withRegisterAt(new Timestamp(System.currentTimeMillis()))
                    .build();

            getBean.userService().save(user);
            log.info("User saved :" + user);
        }
    }
}
