package ru.interview.service.telegramm;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.interview.enums.SectionsLinks;

import java.util.ArrayList;
import java.util.List;

public class SectionKeeBoardService {


    public static InlineKeyboardMarkup getInlineKeeBoard() {
        // ответ под сообщением в виде кнопок
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        // список строк с возможными ответами
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        // одна строка один список
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        // Добавляем в одну линию три кнопки

        rowInline.add(getButton(SectionsLinks.BASIC.getName()));
        rowInline.add(getButton(SectionsLinks.OOP.getName()));
        rowsInLine.add(rowInline);

        List<InlineKeyboardButton> rowInSecondline = new ArrayList<>();
        rowInSecondline.add(getButton(SectionsLinks.EXCEPTIONS.getName()));
        rowInSecondline.add(getButton(SectionsLinks.COLLECTIONS.getName()));
        rowsInLine.add(rowInSecondline);

        List<InlineKeyboardButton> rowInThirdline = new ArrayList<>();
        rowInThirdline.add(getButton(SectionsLinks.STRINGS.getName()));
        rowInThirdline.add(getButton(SectionsLinks.IO.getName()));
        rowsInLine.add(rowInThirdline);

        List<InlineKeyboardButton> rowInForthLine = new ArrayList<>();
        rowInForthLine.add(getButton(SectionsLinks.CONCURRENT.getName()));
        rowsInLine.add(rowInForthLine);

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
