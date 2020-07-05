package ru.studentsplatform.backend.tlgrmbot.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.service.parsers.UniversityScheduleResolver;
import ru.studentsplatform.backend.tlgrmbot.config.BotCommands;
import ru.studentsplatform.backend.tlgrmbot.dataStorage.RunningCommand;
import javax.annotation.PostConstruct;
import java.util.LinkedList;

@Service
public class StudentsPlatformBot extends TelegramLongPollingBot {

    static {
        ApiContextInitializer.init();
    }

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
            if(RunningCommand.isAwaitingData(getChatId(update))) {
                attemptToResolveCommandByCurrentData(update);
            }
            else {
                commandAnalyse(getText(update), getChatId(update));
            }
        }
    }

    /**
     * Возвращает текст сообщения.
     * @param update сообщение от пользователя.
     */
    private String getText(Update update){
        return update.getMessage().getText();
    }

    /**
     * Возвращает Id чата, из которого было отправлено сообщение.
     * @param update сообщение от пользователя.
     */
    private long getChatId(Update update){
        return update.getMessage().getChatId();
    }

    /**
     * Вводит данные. Если их хватает - исполняет текущую команду. В противном случае выходит из метода.
     * @param update сообщение от пользователя.
     */
    private void attemptToResolveCommandByCurrentData(Update update){
        if(RunningCommand.enterData(getChatId(update), getText(update))){
            return;
        }
        switch (transferCommandToEnum(RunningCommand.getRunningCommand(getChatId(update)))){
            case SCHEDULE:
                printSchedule(getChatId(update));
                break;
        }
    }

    /**
     * Создаёт на основе текста команды объект команды.
     * @param command Текст сообщения от пользователя.
     * @return Объект команды, содержащий текст команды и кол-во необходимых сообщений с данными.
     */
    private BotCommands transferCommandToEnum(String command){
        BotCommands botCommand = BotCommands.getCommandObject(command);
        if(botCommand == null){
            return BotCommands.UNKNOWN;
        }
        return botCommand;
    }

    /**
     * Метод производит анализ введённой команды, и, если такая команда доступна, начинает её выполнение.
     * @param commandMessage Команда, отправленная пользователем.
     * @param chatId Id чата, в который будет отправлен ответ.
     */
    private void commandAnalyse(String commandMessage, long chatId) {
        BotCommands command = transferCommandToEnum(commandMessage);
        switch (command) {
            case SCHEDULE:
                    initiateScheduleCommand(command, chatId);
                break;
            default: unknownCommandResponse(chatId);
                break;
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
     * Инициализирует начало выполнения команды отправки календаря. Отправляет сообщения с подсказкой в чат.
     * @param command Объект команды.
     * @param chatId Id чата, в который будет отправлено сообщение.
     */
    private void initiateScheduleCommand(BotCommands command, long chatId){
        setUpCommandParameters(command, chatId);
        sendScheduleHintMessages(chatId);
    }

    private void sendScheduleHintMessages(long chatId){
        sendMessageToUser("Отлично! Теперь мне необходимо знать название твоего универа, направления и группы!" +
                " А также не забудь в конце указать интересующую тебя дату!" +
                " Пожалуйста, отправь названия четырьмя разными сообщениями! К примеру:", chatId);
        sendMessageToUser("СПБГУ", chatId);
        sendMessageToUser("Biology", chatId);
        sendMessageToUser("19.Б01-Б" , chatId);
        sendMessageToUser("Friday" , chatId);
    }

    /**
     * Устанавливает текущую команду и кол-во необходимых сообщений с данными от чата.
     * @param command Объект команды.
     * @param chatId Id чата, в который будет отправлено сообщение.
     */
    private void setUpCommandParameters(BotCommands command, long chatId){
        RunningCommand.establishRunningCommand(chatId,command.getCommandMessage());
        RunningCommand.setAwaitingDataAmount(chatId, command.getRequiredDataNum());
    }

    /**
     * Отправляет пользователю расписание на основе введённых им данных.
     * @param chatId Id чата, в который будет отправлено сообщение.
     */
    private void printSchedule(long chatId) {
        LinkedList<String> data = RunningCommand.getData(chatId);
        data.remove(); //TODO: необходимо реализовать переключение реализваций для разных университетов!
        String schedule = scheduleResolver.getSchedule(data);
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
