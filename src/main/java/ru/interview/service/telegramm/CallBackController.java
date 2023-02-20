package ru.interview.service.telegramm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import ru.interview.common.StringConstant;
import ru.interview.enums.SectionsLinks;
import ru.interview.model.Question;
import ru.interview.model.Section;
import ru.interview.model.Status;
import ru.interview.service.QuestionController;
import ru.interview.service.RandomQuestionService;
import ru.interview.service.SectionController;
import ru.interview.service.StatusController;

import java.util.List;

@Component
public class CallBackController {

    @Autowired
    private ExecutionService executionService;
    @Autowired
    private QuestionController questionController;
    @Autowired
    private StatusController statusController;
    @Autowired
    private SectionController sectionController;

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
            // Внесение информации в таблицу о том какой раздел активен
            sectionController.registerSection(chatId, callbackData);
            getQuestionFromSection(callbackData,messageId,chatId);


        } else if (callbackData.equals(StringConstant.ANOTHER_QUESTION)) {
            String text = "ВОПРОС:";
            // Получение списка вопросов
            Section section = sectionController.findSectionInActiveStatus(chatId);
            getQuestionFromSection(section.getSection(),messageId,chatId);

        } else if (callbackData.equals(StringConstant.ANSWER)) {
            String text = "ПРАВИЛЬНЫЙ_ОТВЕТ: ";
            // получение chatId, и поиск вопроса в таблице по status = ACTIVE, chatId
            Status status = statusController.findQuestionInActiveStatus(chatId);
            Question question = questionController.findAnswer(status.getQuestion());
            executionService.executionEditMessage(CallBackAnswerController.setCallbackMessage(
                    messageId, chatId, question.getAnswer()));
            statusController.closeActiveStatus(chatId);

        } else {
            executionService.executionEditMessage(setCallbackMessage(messageId, chatId, "Бот в помощь!"));
        }
    }

    private void getQuestionFromSection(String callbackData, long messageId, long chatId) {
        // Получение списка вопросов
        List<Question> listOfQuests = questionController.findQuestionBySection(callbackData);
        Question rndQuestion = RandomQuestionService.getQuestionFromList(listOfQuests);
        executionService.executionEditMessage(CallBackQuestionController.setCallbackMessage(
                messageId, chatId, rndQuestion.getQuestion()));
        // создание записи в таблице о том что был задан вопрос
        statusController.registerQuestion(chatId, rndQuestion.getQuestion());
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
