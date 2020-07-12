package ru.studentsplatform.backend.service.entities.Schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 * Класс, представляющий объект распиания за день. Включает список объектов
 * {@link Lesson}, представляющих расписание урока
 *
 * @author Perevalov Pavel (07.07.2020)
 */
public class DaySchedule {
    private final DayOfWeek dayTitle;

    private final LocalDate date;

    private final List<Lesson> lessons;

    /**
     * Конструктор.
     *
     * @param dayTitle хранит день недели, представленный объектом класса
     * @param date     хранит дату дня расписания в формате день-месяц
     * @param lessons  хранит список {@link Lesson}, представляющих объект урока
     */
    public DaySchedule(DayOfWeek dayTitle,
                       LocalDate date,
                       List<Lesson> lessons) {
        this.dayTitle = dayTitle;
        this.date = date;
        this.lessons = lessons;
    }

    public DayOfWeek getDayTitle() {
        return dayTitle;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(dayTitle.getDisplayName(TextStyle.FULL, Locale.ENGLISH))
                .append("\n")
                .append(date)
                .append("\n");
        lessons.forEach(lesson -> sb.append(lesson)
                .append("\n"));

        return String.valueOf(sb);
    }
}
