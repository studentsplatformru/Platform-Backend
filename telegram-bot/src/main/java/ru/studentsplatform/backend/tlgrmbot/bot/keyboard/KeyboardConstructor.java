package ru.studentsplatform.backend.tlgrmbot.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.studentsplatform.backend.entities.model.enums.UniversityEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Утилитный класс, конфигурирует кастомную клавиатуру для пользователя бота.
 */
public class KeyboardConstructor {

    /**
     * Конфигурирует клавиатуру со списком доступных университетов.
     *
     * @return клавиатура с произвольным количеством рядов, каждый из которых вмещает до 3 кнопок.
     */
    public static ReplyKeyboardMarkup getUniversitiesKeyboard() {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (UniversityEnum universityEnum : UniversityEnum.values()) {
            row.add(universityEnum.getName());

            if (row.size() == 3) {
                rows.add(row);
                row = new KeyboardRow();
            }
        }
        rows.add(row);

        return new ReplyKeyboardMarkup(rows)
                .setResizeKeyboard(true);
    }

    /**
     * Конфигурирует клавиатуру со списком доступных направлений.
     *
     * @return клавиатура с единственным элементом кнопки.
     */
    public static ReplyKeyboardMarkup getDirectionsKeyboard() {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Mahematics and Computer Science");
        rows.add(row);

        return new ReplyKeyboardMarkup(rows)
                .setResizeKeyboard(true);
    }

    /**
     * Конфигурирует клавиатуру со списком доступных групп.
     *
     * @return клавиатура с единственным элементом кнопки.
     */
    public static ReplyKeyboardMarkup getGroupsKeyboard() {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("19.Б03-мкн");
        rows.add(row);

        return new ReplyKeyboardMarkup(rows)
                .setResizeKeyboard(true);
    }
}
