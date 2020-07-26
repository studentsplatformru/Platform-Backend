package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.service;

import org.springframework.statemachine.StateMachine;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state.TelegramBotState;

public interface BotService {

    void setInfo(AbsSender absSender, User user, Chat chat);

/*
    void selectUniversity(Update update, AbsSender absSender, StateMachine<TelegramBotState, TelegramBotEvent> stateMachine);

    void selectGroupSpbu(Update update, AbsSender absSender, StateMachine<TelegramBotState, TelegramBotEvent> stateMachine);

    void save(Update update, AbsSender absSender);
*/

    void define(Update update, AbsSender absSender);
}
