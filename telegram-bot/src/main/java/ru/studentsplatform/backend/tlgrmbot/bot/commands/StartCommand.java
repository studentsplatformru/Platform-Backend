package ru.studentsplatform.backend.tlgrmbot.bot.commands;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.tlgrmbot.bot.Text;

@Service
public class StartCommand extends BotCommand {

    private static final Logger logger = LogManager.getLogger(StartCommand.class);

    public StartCommand() {
        super("start", Text.START_DESCRIPTION.toString());
    }

    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String greetingsTitle = String.format("Привет, %s!\n", user.getFirstName());

        SendMessage startInfo = new SendMessage();
        startInfo.setChatId(chat.getId().toString());
        startInfo.setText(greetingsTitle + Text.START_MESSAGE.toString());

        printMessage(absSender, startInfo);
    }

    private void printMessage(AbsSender absSender, SendMessage answer) {
        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            logger.log(Level.ERROR, e);
        }
    }
}