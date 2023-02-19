package ru.interview.service.telegramm;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.interview.common.StringConstant;
import ru.interview.enums.SectionsLinks;

import java.util.ArrayList;
import java.util.List;

public class QuestionKeeBoardService {

    public static InlineKeyboardMarkup getInlineKeeBoard() {
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(getButton(StringConstant.ANSWER));
        rowsInLine.add(rowInline);
        markupInLine.setKeyboard(rowsInLine);

        return markupInLine;
    }

    private static InlineKeyboardButton getButton(String response) {
        var button = new InlineKeyboardButton();
        button.setText(response);
        button.setCallbackData(response);
        return button;

    }
}
