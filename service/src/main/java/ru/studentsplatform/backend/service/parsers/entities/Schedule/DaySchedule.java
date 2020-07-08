package ru.studentsplatform.backend.service.parsers.entities.Schedule;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 * Класс, представляющий объект распиания за день. Включает
 */
public class DaySchedule {
    private final DayOfWeek dayTitle;
    private final List<Lesson> lessons;

    /**
     * Конструктор.
     *
     * @param dayTitle хранит день недели, представленный объектом класса
     * @param lessons хранит список {@link Lesson}, представляющих объект урока
     */
    public DaySchedule(DayOfWeek dayTitle,
                       List<Lesson> lessons) {
        this.dayTitle = dayTitle;
        this.lessons = lessons;
    }

    public DayOfWeek getDayTitle() {
        return dayTitle;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    @Override
    public String toString() {
        return String.format("{%s, %s}",
                dayTitle.getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                lessons);
    }
}
