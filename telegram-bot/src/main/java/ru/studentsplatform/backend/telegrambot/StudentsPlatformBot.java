package ru.studentsplatform.backend.telegrambot;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.entities.model.enums.Emoji;
import ru.studentsplatform.backend.telegrambot.commands.StartCommand;
import ru.studentsplatform.backend.telegrambot.statemachine.service.BotService;
import ru.studentsplatform.backend.telegrambot.commands.HelpCommand;
import ru.studentsplatform.backend.telegrambot.commands.ScheduleCommand;
import ru.studentsplatform.backend.telegrambot.commands.SetInfoCommand;

import javax.annotation.PostConstruct;

/**
 * Класс бота отвечает за регистрацию команд, которые доступны боту,
 * а также обработку входящих update от пользователя.
 */
@Service
//@PropertySource("application.yml")
@ConfigurationProperties("telegram-bot")
public class StudentsPlatformBot extends TelegramLongPollingCommandBot {

    private static final Logger LOGGER = LogManager.getLogger(StudentsPlatformBot.class);
    private final BotService botService;

    /**
     * Конструктор. Здесь регистрируются команды бота.
     *
     * @param startCommand    {@link StartCommand}
     * @param scheduleCommand {@link ScheduleCommand}
     * @param setInfoCommand  {@link SetInfoCommand}
     * @param botService      с помощью {@link org.springframework.statemachine.StateMachine} обрабатывает
     *                        update в зависимости от текущего состояния пользователя.
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
            commandUnknownMessage.setText("Команда '" + message.getText() +
                    "' неизвестна этому боту. Список доступных команд " + Emoji.AMBULANCE);
            try {
                absSender.execute(commandUnknownMessage);
            } catch (TelegramApiException e) {
                LOGGER.log(Level.ERROR, e);
            }
            helpCommand.execute(absSender, message.getFrom(), message.getChat(), new String[]{});
        });
    }

    /**
     * Обработка update от пользователя, если это не команда.
     *
     * @param update сообщение от пользователя, включающее текст.
     */
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
            LOGGER.log(Level.FATAL, e);
        }
    }

    @Override
    public String getBotToken() {
        return "1098099445:AAF2rv2AgzkE1BNGaixWo-b-8-Jktv0l0xw";
    }

    @Override
    public String getBotUsername() {
        return "students_platform_bot";
    }
}