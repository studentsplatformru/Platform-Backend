package ru.studentsplatform.backend.tlgrmbot.bot.commands;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.service.BotService;

@Service
public class MessageProcessor {

    private static BotService botService;

    public MessageProcessor(BotService botService) {
        MessageProcessor.botService = botService;
    }

    public static void analyze(Update update, AbsSender absSender) {
        String text = update.getMessage().getText();

        switch (text) {
            case "СПБГУ":
                botService.selectSpbu(update, absSender);
                break;
            case "19.Б03-мкн":
                botService.selectGroupSpbu(update, absSender);
                break;
            case "Ok":
                botService.save(update, absSender);
                break;
        }
    }
}
