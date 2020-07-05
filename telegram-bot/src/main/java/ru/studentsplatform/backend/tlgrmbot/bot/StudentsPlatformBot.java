package ru.studentsplatform.backend.tlgrmbot.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.service.parsers.UniversityScheduleResolver;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedList;

@Service
public class StudentsPlatformBot extends TelegramLongPollingBot {

    // Оставь надежду всяк сюда входящий...

    static {
        ApiContextInitializer.init();
    }

    /**
     * Эта HashMap содержит пары ChatId <-> Количество ожидаемых от конкретного чата сообщений с данными.
     */
    private HashMap<Long, Integer> awaitingDataMessagesFromUserCount = new HashMap<>();
    /**
     * Эта HashMap содержит пары ChatId <-> Лист сообщений с данными, необходимыми для исполнения текущей команды для конкретного чата.
     */
    private HashMap<Long,LinkedList<String>> dataPool = new HashMap<>();
    /**
     * Эта HashMap содержит пары ChatId <-> Обрабатываемая команда для конкретного чата.
     */
    private HashMap<Long, String> currentCommandResolving = new HashMap<>();;

    private final UniversityScheduleResolver scheduleResolver;

    public StudentsPlatformBot(UniversityScheduleResolver scheduleResolver) {
        this.scheduleResolver = scheduleResolver;
    }

    /**
     * Метод вызывается каждый раз, когда бот получает сообщение.
     * Если бот находится в процессе выполнения команды - происходит ввод данных в пул данных.
     * Если бот не находится в процессе выполнения команды - инициализирует выполнение команды или возвращает ответ о неизвестной команде.
     * @param update Сообщение, приходящее от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if(awaitingDataMessagesFromUserCount.get(update.getMessage().getChatId())!= null &&
                    !awaitingDataMessagesFromUserCount.get(update.getMessage().getChatId()).equals(0)){
                enterAwaitingData(update.getMessage().getText(), update.getMessage().getChatId());
            }
            else if (update.getMessage().getText().startsWith("/")) {
                awaitingDataMessagesFromUserCount.put(update.getMessage().getChatId(),0);
                commandAnalyse(update.getMessage().getText(), update.getMessage().getChatId());
            }
            else {
                unknownCommandResponse(update.getMessage().getChatId());
            }
        }
    }

    /**
     * Добавляет данные в пул и уменьшает количество ожидаемых сообщений с данными.
     * Если после вызова метода новых данных не ожидается - происходит исполнение команды на основе собранных данных.
     * @param data Сообщение от пользователя с данными, необходимыми для исполнения команды.
     * @param chatId Id чата, от которого было получено сообщение.
     */
    private void enterAwaitingData(String data, long chatId){
        dataPool.get(chatId).add(data);
        reduceNumberOfAwaitingDataMessages(chatId);
        if(awaitingDataMessagesFromUserCount.get(chatId).equals(0)){
            resolveCommand(chatId);
        }
    }

    /**
     * Уменьшает количество необходимых ко вводу данных на 1.
     * @param chatId Id чата, для которого количество ожидаемых данных будет уменьшено.
     */
    private void reduceNumberOfAwaitingDataMessages(long chatId){
        awaitingDataMessagesFromUserCount.replace(chatId, awaitingDataMessagesFromUserCount.get(chatId)-1);
    }

    /**
     * Исполняет текующую команду.
     * @param chatId Id чата, в рамках которого будет выполнена команда.
     */
    private void resolveCommand(long chatId){
        switch (currentCommandResolving.get(chatId)){
            case "/schedule":
                printSchedule(chatId);
                break;
        }
    }

    /**
     * Отправляет сообщение в чат.
     * @param message Сообщение для отправки пользователю.
     * @param chatId Id чата, в который будет отправлено сообщение.
     */
    private void sendMessageToUser(String message, long chatId){
        SendMessage sendMessage = new SendMessage().setChatId(chatId).setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправляет пользователю сообщение о вводе неизвестной команды.
     * @param chatId Id чата, в который будет отправлено сообщение.
     */
    private void unknownCommandResponse(long chatId){
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText("Извини, в ответах я ограничен - правильно задавай вопросы");
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод производит анализ введённой команды, и, если такая команда доступна, начинает её выполнение,
     * устанавливая количество сообщений с данными, которые будут ожидаться от пользователя.
     * @param command Команда, отправленная пользователем.
     * @param chatId Id чата, в который будет отправлен ответ.
     */
    private void commandAnalyse(String command, long chatId) {
        switch (command) {
            case "/schedule":
                    awaitingDataMessagesFromUserCount.replace(chatId, 4);
                    currentCommandResolving.put(chatId,command);
                    dataPool.put(chatId,new LinkedList<String>());
                    sendMessageToUser("Отлично! Теперь мне необходимо знать название твоего универа, направления и группы!" +
                            " А также не забудь в конце указать интересующую тебя дату!" +
                            " Пожалуйста, отправь названия четырьмя разными сообщениями! К примеру:", chatId);
                    sendMessageToUser("СПБГУ", chatId);
                    sendMessageToUser("Biology", chatId);
                    sendMessageToUser("19.Б01-Б" , chatId);
                sendMessageToUser("Friday" , chatId);
                break;
            default: unknownCommandResponse(chatId);
                break;
        }
    }

    /**
     * Отправляет пользователю расписание на основе введённых им данных.
     * @param chatId Id чата, в который будет отправлено сообщение.
     */
    private void printSchedule(long chatId) {
        String university = dataPool.get(chatId).remove();
        String study = dataPool.get(chatId).remove();
        String groupName = dataPool.get(chatId).remove();
        String date = dataPool.get(chatId).remove();
        String schedule = scheduleResolver.getSchedule(university, study, groupName, date);
        sendMessageToUser(schedule,chatId);
    }

    @Override
    public String getBotUsername() {
        return "StudPlatformTestBot";
    }

    @Override
    public String getBotToken() {
        return "1334599795:AAG0yj3g1P5E4fwjpTADqoZ706OMNn-DlJQ";
    }

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
