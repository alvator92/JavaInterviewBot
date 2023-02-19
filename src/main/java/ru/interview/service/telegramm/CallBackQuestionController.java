package ru.interview.service.telegramm;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class CallBackQuestionController {

    public static EditMessageText setCallbackMessage(long messageId, long chatId, String text) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(chatId);
        messageText.setText(text);
        messageText.setMessageId((int)messageId);
        messageText.setReplyMarkup(QuestionKeeBoardService.getInlineKeeBoard());
        return messageText;
    }
}
