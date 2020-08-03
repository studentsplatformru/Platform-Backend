package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.service;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface BotService {

	void setInfo(AbsSender absSender, User user, Chat chat);

	void define(Update update, AbsSender absSender);
}
