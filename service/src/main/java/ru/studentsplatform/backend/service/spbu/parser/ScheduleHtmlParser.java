package ru.studentsplatform.backend.service.spbu.parser;

import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Интерфейс представляет единую форму парсера расписания определенной
 * html-страницы произвольного университета.
 */
public interface ScheduleHtmlParser {
    /**
     * Метод, который должен возвращать объект расписания дня.
     *
     * @param currentDay интересуемый день недели
     * @param requestedUrl адрес html-страницы с расписанием произвольного университета
     * @return объект, представляющий расписание за один день
     */
    DaySchedule getDaySchedule(DayOfWeek currentDay, String requestedUrl);

    /**
     * Метод, который должен возвращать список объектов расписаний за неделю.
     *
     * @param requestedUrl адрес html-страницы с расписанием произвольного университета
     * @return список объектов, представляющих расписание за день
     */
    List<DaySchedule> getWeekSchedule(String requestedUrl);
}
