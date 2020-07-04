package ru.studentsplatform.backend.tlgrmbot.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.service.parsers.spbu.SpbuScheduleFinder;
import ru.studentsplatform.backend.service.parsers.spbu.SpbuScheduleParser;

import javax.annotation.PostConstruct;

@Service
public class StudentsPlatformBot extends TelegramLongPollingBot {

    static {
        ApiContextInitializer.init();
    }

    private final SpbuScheduleParser scheduleParser;

    private final SpbuScheduleFinder scheduleFinder;


    public StudentsPlatformBot(SpbuScheduleParser scheduleParser, SpbuScheduleFinder scheduleFinder) {
        this.scheduleParser = scheduleParser;
        this.scheduleFinder = scheduleFinder;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {


            if (update.getMessage().getText().startsWith("/")) {
                commandAnalyse(update.getMessage().getText(), update.getMessage().getChatId());
            }

            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("test message");
            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void commandAnalyse(String command, Long id) {
        switch (command) {
            case "/schedule":
                SendMessage sendMessage = new SendMessage().setChatId(id).setText(printSchedule());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    private String printSchedule() {
        scheduleParser.setConnection(scheduleFinder.findScheduleLink("Mathematics and Mechanics", "19.Б04-мм"));
        return scheduleParser.getDailySchedule("Monday");
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
