package ru.studentsplatform.backend.tlgrmbot.bot.commands;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.studentsplatform.backend.tlgrmbot.bot.Text;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.service.BotService;

@Service
public class SetInfoCommand extends BotCommand {

    private final BotService botService;

    public SetInfoCommand(BotService botService) {
        super("setinfo", Text.SET_INFO_DESCRIPTION.toString());
        this.botService = botService;
    }

    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        botService.setInfo(absSender, user, chat);
    }
}