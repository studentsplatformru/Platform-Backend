package ru.studentsplatform.backend.tlgrmbot.bot.old;

import org.telegram.abilitybots.api.util.AbilityUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.studentsplatform.backend.tlgrmbot.bot.KeyboardConstructor;

import static ru.studentsplatform.backend.tlgrmbot.bot.Text.*;

public class ReplyConstructor {
    public static SendMessage getUniversityMessageAndKeyboard(Update update) {
        return new SendMessage()
                .setText(UNIVERSITY_STATE.toString())
                .setChatId(AbilityUtils.getChatId(update))
                .setReplyMarkup(KeyboardConstructor.getUniversitiesKeyboard());
    }

    public static SendMessage getDirectionMessageAndKeyboard(Update update) {
        return new SendMessage()
                .setText(DIRECTION_STATE.toString())
                .setChatId(AbilityUtils.getChatId(update))
                .setReplyMarkup(KeyboardConstructor.getDirectionsKeyboard());
    }

    public static SendMessage getGroupMessageAndKeyboard(Update update) {
        return new SendMessage()
                .setText(GROUP_STATE.toString())
                .setChatId(AbilityUtils.getChatId(update))
                .setReplyMarkup(KeyboardConstructor.getGroupsKeyboard());
    }
}
