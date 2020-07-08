package ru.studentsplatform.backend.service.parsers;

import ru.studentsplatform.backend.service.parsers.entities.DaySchedule;

import java.time.DayOfWeek;

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
    DaySchedule getDailySchedule(DayOfWeek currentDay, String requestedUrl);
}
