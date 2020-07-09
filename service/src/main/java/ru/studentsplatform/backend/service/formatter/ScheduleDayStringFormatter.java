package ru.studentsplatform.backend.service.formatter;

import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;

/**
 * Класс предназначен для форматирования форматирования объектов в удобочитаемую форму.
 */
public class ScheduleDayStringFormatter {

    /**
     * @param day Календарный день с расписанием.
     * @return Форматированая строка с расписанием.
     */
    public static String formatJson(DaySchedule day) {
        String formattedString = day.toString().replace(',', '\n');
        formattedString = formattedString.replace("{", "\t");
        formattedString = formattedString.replace("=", " = ");
        formattedString = formattedString.replace("'", "");
        formattedString = formattedString.replace("[", "");
        return formattedString;
    }

}
