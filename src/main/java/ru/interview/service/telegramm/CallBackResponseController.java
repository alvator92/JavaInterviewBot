package ru.interview.service.telegramm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import ru.interview.common.StringConstant;

import java.util.List;

@Component
public class CallBackResponseController {

    @Autowired
    private ExecutionService executionService;

    /**
     * Получение ответа после того как пользователь нажимает кнопку ДА/НЕТ
     */
    public void getCallbackDataFromUser(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        if (callbackData.equals(StringConstant.YES_BUTTON)) {
            String text = "Окей, какой раздел тебя интереусует?";
            executionService.executionEditMessage(setCallbackMessage(messageId, chatId, text));

        } else if (callbackData.equals(StringConstant.NO_BUTTON)) {
            String text = "Надумаешь, приходи!";
            // Обработка сообщещний по кнопке
            executionService.executionEditMessage(setCallbackMessage(messageId, chatId, text));
        } else {
            executionService.executionEditMessage(setCallbackMessage(messageId, chatId, "Бот в помощь!"));
        }
    }

    /**
     * Обработка сообщещний после нажатия кнопки YES/NO
     */
    private EditMessageText setCallbackMessage(long messageId, long chatId, String text) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(chatId);
        messageText.setText(text);
        messageText.setMessageId((int)messageId);
        return messageText;
    }
}
