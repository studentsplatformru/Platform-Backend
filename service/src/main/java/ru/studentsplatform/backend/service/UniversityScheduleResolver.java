package ru.studentsplatform.backend.service;

import ru.studentsplatform.backend.service.entities.Respondent;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.enums.University;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Объединяет в себе функционал интерфейсов ScheduleHtmlParser и ScheduleUrlFinder.
 */
public interface UniversityScheduleResolver {
    /**
     * Возвращает объект расписания за день для выбраного университета.
     *
     * @param dayOfWeek  День недели, для которого необходимо получить расписание.
     * @return Объект расписания, содержащий поля с данными о расписании.
     */
    DaySchedule getDaySchedule(Respondent respondent, DayOfWeek dayOfWeek);
    /**
     * Возвращает объект расписания за неделю для выбраного университета.
     *
     * @return Объект расписания, содержащий поля с данными о расписании.
     */
    List<DaySchedule> getSchedule(Respondent respondent);
}
