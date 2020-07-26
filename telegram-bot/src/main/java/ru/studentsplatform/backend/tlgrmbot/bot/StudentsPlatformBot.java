package ru.studentsplatform.backend.tlgrmbot.bot;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.service.entities.enums.Emoji;
import ru.studentsplatform.backend.tlgrmbot.bot.commands.*;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.service.BotService;

import javax.annotation.PostConstruct;

@Service
public class StudentsPlatformBot extends TelegramLongPollingCommandBot {

    private static final Logger logger = LogManager.getLogger(StudentsPlatformBot.class);
    private final BotService botService;

    /**
     * Constructor.
     *
     * @param startCommand    a
     * @param scheduleCommand b
     * @param setInfoCommand  c
     */
    public StudentsPlatformBot(StartCommand startCommand,
                               ScheduleCommand scheduleCommand,
                               SetInfoCommand setInfoCommand,
                               BotService botService) {
        this.botService = botService;

        HelpCommand helpCommand = new HelpCommand(this);
        registerAll(helpCommand,
                startCommand,
                scheduleCommand,
                setInfoCommand);

        registerDefaultAction((absSender, message) -> {
            SendMessage commandUnknownMessage = new SendMessage();
            commandUnknownMessage.setChatId(message.getChatId());
            commandUnknownMessage.setText("The command '" + message.getText() +
                    "' is not known by this bot. Here comes some help " + Emoji.AMBULANCE);
            try {
                absSender.execute(commandUnknownMessage);
            } catch (TelegramApiException e) {
                logger.log(Level.ERROR, e);
            }
            helpCommand.execute(absSender, message.getFrom(), message.getChat(), new String[]{});
        });
    }

    @Override
    public void processNonCommandUpdate(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                botService.define(update, this);
            }
        }
    }

    /**
     * Регистрирует и запускает инстанс бота.
     */
    @PostConstruct
    public void registerBot() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            logger.log(Level.FATAL, e);
        }
    }

    @Override
    public String getBotToken() {
        return "1334599795:AAG0yj3g1P5E4fwjpTADqoZ706OMNn-DlJQ";
    }

    @Override
    public String getBotUsername() {
        return "StudPlatformTestBot";
    }
}