package ru.studentsplatform.backend.tlgrmbot.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static ru.studentsplatform.backend.service.entities.enums.University.*;

public class KeyboardConstructor {

    public static ReplyKeyboardMarkup getUniversitiesKeyboard() {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(SPBU.toString());
        row.add(ITMO.toString());
        row.add(SPBSTU.toString());
        rows.add(row);

        return new ReplyKeyboardMarkup(rows)
                .setResizeKeyboard(true)
                .setOneTimeKeyboard(true);
    }

    public static ReplyKeyboardMarkup getDirectionsKeyboard() {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Mahematics and Computer Science");
        rows.add(row);

        return new ReplyKeyboardMarkup(rows)
                .setResizeKeyboard(true)
                .setOneTimeKeyboard(true);
    }

    public static ReplyKeyboardMarkup getGroupsKeyboard() {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("19.Б03-мкн");
        rows.add(row);

        return new ReplyKeyboardMarkup(rows)
                .setResizeKeyboard(true)
                .setOneTimeKeyboard(true);
    }
}
