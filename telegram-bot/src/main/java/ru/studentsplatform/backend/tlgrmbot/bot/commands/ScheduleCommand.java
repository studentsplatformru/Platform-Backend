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
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.tlgrmbot.bot.InfoTransmitter;
import ru.studentsplatform.backend.tlgrmbot.bot.Text;

import java.util.List;

@Service
public class ScheduleCommand extends BotCommand {

    private static final Logger LOGGER = LogManager.getLogger(ScheduleCommand.class);
    private final InfoTransmitter infoTransmitter;

    /**
     * Construct.
     *
     * @param infoTransmitter d
     */
    public ScheduleCommand(InfoTransmitter infoTransmitter) {
        super("schedule", Text.SCHEDULE_DESCRIPTION.toString());
        this.infoTransmitter = infoTransmitter;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        List<DaySchedule> schedules = infoTransmitter.getScheduleById(user.getId());

        SendMessage message = new SendMessage()
                .setChatId(chat.getId());

        for (DaySchedule schedule : schedules) {
            message.setText(schedule.toString());
            printMessage(absSender, message);
        }
    }

    private void printMessage(AbsSender absSender, SendMessage answer) {
        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}
