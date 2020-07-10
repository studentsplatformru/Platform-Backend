package ru.studentsplatform.backend.tlgrmbot.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.service.UniversityScheduleResolverImpl;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.enums.University;
import ru.studentsplatform.backend.tlgrmbot.config.BotCommands;
import ru.studentsplatform.backend.tlgrmbot.dataStorage.RunningCommand;
import ru.studentsplatform.backend.service.formatter.ScheduleDayStringFormatter;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.LinkedList;

@Service
public class StudentsPlatformBot extends TelegramLongPollingBot {

    static {
        ApiContextInitializer.init();
    }

    private final UniversityScheduleResolverImpl universityScheduleResolverImpl;

    public StudentsPlatformBot(UniversityScheduleResolverImpl universityScheduleResolverImpl) {
        this.universityScheduleResolverImpl = universityScheduleResolverImpl;
    }

    /**
     * Метод вызывается каждый раз, когда бот получает сообщение.
     * Если команда уже выполняется и требуются параметры - выполняет команду с вошедшими параметрами.
     * Иначе начинает выполнять новую команду (если она валидная).
     * @param update Сообщение от пользователя с командой или параметрами команды.
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (RunningCommand.seeRunningCommand(getChatId(update)).isRequiredParameters()) {
                resolveCommand(update);
            } else {
                startCommand(getChatId(update), getText(update));
            }
        }
    }

    /**
     * Возвращает текст сообщения.
     * @param update сообщение от пользователя.
     * @return Текст сообщения.
     */
    private String getText(Update update) {
        return update.getMessage().getText();
    }

    /**
     * Возвращает Id чата, из которого было отправлено сообщение.
     * @param update сообщение от пользователя.
     * @return Id чата.
     */
    private long getChatId(Update update) {
        return update.getMessage().getChatId();
    }

    /**
     * Выполняет команду на основе полученных параметров.
     * @param update сообщение от пользователя.
     */
    private void resolveCommand(Update update) {
        switch (RunningCommand.pullRunningCommand(getChatId(update))) {
            case SCHEDULE:
                printSchedule(update);
                break;
            default: sendHintMessages(getChatId(update));
        }
    }

    /**
     * Создаёт объект команды и отправляет подсказку.
     * @param commandMessage Команда, отправленная пользователем.
     * @param chatId Id чата, к которому будет привязана команда и в который будет отправлен ответ.
     */
    private void startCommand(long chatId, String commandMessage) {
        transformMessageToCommandObject(chatId, commandMessage);
        sendHintMessages(chatId);
    }

    /**
     * Создаёт на основе текста объект команды и прикрепляет её к чату.
     * @param chatId Id чата, от которого была получена команда.
     * @param command Текст сообщения от пользователя.
     */
    private void transformMessageToCommandObject(long chatId, String command) {
        BotCommands botCommand = BotCommands.getBotCommandByName(command);
        if (botCommand == null) {
            botCommand = BotCommands.UNKNOWN;
        }
        RunningCommand.establishRunningCommand(chatId, botCommand);
    }

    private void sendHintMessages(long chatId) {
        switch (RunningCommand.seeRunningCommand(chatId)) {
            case SCHEDULE:
                sendMessageToUser(
                        "Отлично! Теперь мне необходимо знать название твоего универа, направления и группы!" +
                        " А также не забудь в конце указать интересующую тебя дату!" +
                        " Пожалуйста, отправь названия четырьмя разными сообщениями! К примеру:", chatId);
                sendMessageToUser("СПБГУ; Biology; 19.Б01-Б; Friday", chatId);
                break;
            default:
                sendMessageToUser("Извини, в ответах я ограничен - правильно задавай вопросы", chatId);
                break;
        }
    }

    /**
     * Отправляет сообщение в чат.
     * @param message Сообщение для отправки пользователю.
     * @param chatId Id чата, в который будет отправлено сообщение.
     */
    private void sendMessageToUser(String message, long chatId) {
        SendMessage sendMessage = new SendMessage().setChatId(chatId).setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------Тела команд бота------------------------------------------//

    /**
     * Отправляет пользователю расписание на основе введённых им данных.
     * @param update Сообщение от пользователя с параметрами для команды.
     */
    private void printSchedule(Update update) {
    }

    //---------------------------------------Служебные методы------------------------------------------//

    @Override
    public String getBotUsername() {
        return "StudPlatformTestBot";
    }

    @Override
    public String getBotToken() {
        return "1334599795:AAG0yj3g1P5E4fwjpTADqoZ706OMNn-DlJQ";
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
            System.out.println("registred");
        }
    }
}
