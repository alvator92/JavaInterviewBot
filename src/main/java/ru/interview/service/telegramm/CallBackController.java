package ru.interview.service.telegramm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import ru.interview.common.StringConstant;
import ru.interview.enums.SectionsLinks;
import ru.interview.model.Question;
import ru.interview.service.QuestionController;
import ru.interview.service.RandomQuestionService;

import java.util.List;

@Component
public class CallBackController {

    @Autowired
    private ExecutionService executionService;
    @Autowired
    private QuestionController questionController;

    /**
     * Получение ответа после того как пользователь нажимает кнопку ДА/НЕТ
     */
    public void getCallbackDataFromUser(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        if (callbackData.equals(StringConstant.YES_BUTTON)) {
            String text = "Окей, какой раздел тебя интереусует?";
            executionService.executionEditMessage(CallBackSectionController.setCallbackMessage(messageId, chatId, text));

        } else if (callbackData.equals(StringConstant.NO_BUTTON)) {
            String text = "Надумаешь, приходи!";
            // Обработка сообщещний по кнопке
            executionService.executionEditMessage(setCallbackMessage(messageId, chatId, text));
        } else if (isSection(callbackData)) {
            String text = "ВНИМАНИЕ_ВОПРОС!";
            // Получение списка вопросов
            List<Question> listOfQuests = questionController.findQuestionBySection(callbackData);
            Question rndQuestion = RandomQuestionService.getQuestionFromList(listOfQuests);
            executionService.executionEditMessage(CallBackQuestionController.setCallbackMessage(
                    messageId, chatId, rndQuestion.getQuestion()));
            // создание записи в таблице о том что был задан вопрос

        } else if (callbackData.equals(StringConstant.ANSWER)) {
            String text = "ВНИМАНИЕ_ВОПРОС!";
            // получение chatId, и поиск вопроса в таблице по status = ACTIVE, chatId


        } else {
            executionService.executionEditMessage(setCallbackMessage(messageId, chatId, "Бот в помощь!"));
        }
    }

    private boolean isSection(String callbackData) {
        for(SectionsLinks section : SectionsLinks.values()) {
            if (callbackData.equals(section.getName())){
                return true;
            }
        }
        return false;
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
