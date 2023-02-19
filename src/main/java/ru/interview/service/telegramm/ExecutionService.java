package ru.interview.service.telegramm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Component
@Slf4j
public class ExecutionService {

    @Autowired
    private TelegramBot telegramBot;

    /**
     * Подготовка сообщения к отправлению
     */
    public void prepareAndSendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        executeMessage(message);
    }

    /**
     * Подготовка сообщения к отправлению с клавиатурой для ответа
     */
    public void prepareAndSendMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Какой раздел тебя интересует?");
        message.setReplyMarkup(SectionKeeBoardService.getInlineKeeBoard());
        executeMessage(message);
    }


    public void prepareAndSendMessage(long chatId, String imagePath, String imageCaption) {
        InputFile inputFile;
        try {
             inputFile = new InputFile(new FileInputStream(imagePath), imageCaption);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(inputFile);
        executeMessage(sendPhoto);

    }

    public void prepareAndSendMessage(long chatId, InputStream targetStream, String imageCaption) {
        InputFile inputFile;
        inputFile = new InputFile(targetStream, imageCaption);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(inputFile);
        executeMessage(sendPhoto);

    }

    /**
     * Отправление сообщения
     * @param message
     */
    protected void executeMessage(SendMessage message) {
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения : " + e);
            e.printStackTrace();
        }
    }
    /**
     * Отправление сообщения c фото
     */
    protected void executeMessage(SendPhoto sendPhoto) {
        try {
            telegramBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения : " + e);
            e.printStackTrace();
        }
    }

    /**
     * Отправка сообщения из кнопки
     */
    protected void executionEditMessage(EditMessageText message) {
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения : " + e);
            e.printStackTrace();
        }
    }
}
