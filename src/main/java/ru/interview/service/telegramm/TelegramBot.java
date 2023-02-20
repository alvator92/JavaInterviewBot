package ru.interview.service.telegramm;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.interview.common.StringConstant;
import ru.interview.configuration.BotConfiguration;
import ru.interview.service.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private ExecutionService executionService;
    @Autowired
    private UserController userController;
    @Autowired
    private OwnerController ownerController;
    @Autowired
    private FindQuestionService findQuestion;
    @Autowired
    private CallBackController callBackController;
    @Autowired
    private SectionController sectionController;
    @Autowired
    private StatusController statusController;

    private final BotConfiguration botConfiguration;

    static final String HELP_TEXT = "Этот бот выполнен для того, чтобы помочь тебе подготовиться к Интервью по Java\n\n" +
            "Ты можешь ввести следующие команды из главного меню \n\n" +
            "/start - чтобы увидеть приветствие \n\n" +
            "/find 'question' - чтобы найти нужный ответ введи вопрос через пробел \n\n" +
            "/section - чтобы увидеть список разделов \n\n" +
            "/help - чтобы снова увидеть это сообщение";

    /**
     * Инициализация главного меню
     */
    public TelegramBot(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "Привет Бот!"));
        botCommandList.add(new BotCommand("/section", "Выбери тему по которой ты бы хотел проверить себя"));
        botCommandList.add(new BotCommand("/find", "Либо введи - /find 'question'"));
        botCommandList.add(new BotCommand("/help", "Как пользоваться ботом?"));
        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e ) {
            log.error("Error setting bot`s command list : " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getName();
    }

    @Override
    public String getBotToken() {
        return botConfiguration.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            getMessageTextFromUser(update);

        } else if (update.hasCallbackQuery()) {
            callBackController.getCallbackDataFromUser(update);

        }
    }

    /**
     * Получение сообщения от пользователя
     */
    private void getMessageTextFromUser(Update update) {

        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        // отправка сообщений всем пользователям от админа
        if (messageText.contains("/send") && chatId == botConfiguration.getOwner()) {
            ownerController.sendMessageToUsersFromOwner(messageText);
        } else if (messageText.contains("/find")) {
            var textToSend = messageText.substring(messageText.indexOf(" ")).trim();
            findQuestion.findQuestionByName(chatId, textToSend);
        } else {
            switch (messageText) {
                case "/start" :
                    // проверка пользователя на наличие в БД и создание нового
                    userController.registerUser(update.getMessage());
                    startCommandRecieved(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/section" :
                    executionService.prepareAndSendMessage(chatId);
                    // закрыть активные статусы если таковые есть
                    sectionController.closeActiveStatus(chatId);
                    statusController.closeActiveStatus(chatId);

                    break;
                case "/help" :
                    executionService.prepareAndSendMessage(chatId, HELP_TEXT);
                    break;
                default:
                    executionService.prepareAndSendMessage(chatId, StringConstant.VALIDATION_ERROR_MESSAGE);

            }
        }
    }

    /**
     * Подготовка ответа при команде /start с использованием emoji
     */
    private void startCommandRecieved(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you!" + ":blush:");
        executionService.prepareAndSendMessage(chatId, answer);
    }
}
