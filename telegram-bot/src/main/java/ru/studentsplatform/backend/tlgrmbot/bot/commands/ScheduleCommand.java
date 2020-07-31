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
import ru.studentsplatform.backend.tlgrmbot.bot.InfoTransmitter;
import ru.studentsplatform.backend.tlgrmbot.bot.Text;

import java.util.List;

/**
 * Комманда /schedule предоставляет пользователю расписание его занятий в университете.
 */
@Service
public class ScheduleCommand extends BotCommand {

    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(ScheduleCommand.class);
    private final InfoTransmitter infoTransmitter;

    /**
     * Конструктор. Присваивает имя и описание команды.
     *
     * @param infoTransmitter {@link InfoTransmitter}
     */
    public ScheduleCommand(InfoTransmitter infoTransmitter) {
        super("schedule", Text.SCHEDULE_DESCRIPTION.toString());
        this.infoTransmitter = infoTransmitter;
    }

    /**
     * Предоставляет пользователю расписание за текующую неделю: каждый день как отдельное сообщение.
     *
     * @param absSender объект Telegram-bot для выполнения действия
     * @param user      объект пользователя Telegram
     * @param chat      объект диалога пользователя с ботом
     * @param arguments аргументы, передаваемые с командой (в данном случае не влияет на логику)
     */
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        List<String> schedules = infoTransmitter.getScheduleById(user.getId());

        SendMessage message = new SendMessage()
                .setChatId(chat.getId());

        for (String schedule : schedules) {
            message.setText(schedule);
            printMessage(absSender, message);
        }
    }

    /**
     * Отправляет сообщение пользователю в чат.
     *
     * @param absSender объект Telegram-bot для выполнения действия
     * @param answer    объект сообщения
     */
    private void printMessage(AbsSender absSender, SendMessage answer) {
        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}
