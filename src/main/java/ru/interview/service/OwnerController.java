package ru.interview.service;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.interview.model.User;
import ru.interview.service.telegramm.ExecutionService;

@Component
public class OwnerController {

    @Autowired
    private ExecutionService executionService;
    @Autowired
    private UserController userController;

    /**
     * Отправление рассылки всем пользователям от Владельца бота
     */
    public void sendMessageToUsersFromOwner(String messageText) {
        var textTosend = EmojiParser.parseToUnicode((messageText.substring(messageText.indexOf(" "))));
        var users = userController.getUserList();
        for (User user : users) {
            executionService.prepareAndSendMessage(user.getChatId(), textTosend);
        }
    }
}
