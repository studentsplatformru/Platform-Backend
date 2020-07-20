package ru.studentsplatform.backend.service.spbu;

import ru.studentsplatform.backend.service.entities.Respondent;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Объединяет в себе функционал интерфейсов ScheduleHtmlParser и ScheduleUrlFinder.
 */
public interface OldUniversityScheduleResolver {
    /**
     * Возвращает объект расписания за день для выбраного университета.
     *
     * @param respondent объект класса с информацией о пользователе
     * @param dayOfWeek  День недели, для которого необходимо получить расписание.
     * @return Объект расписания, содержащий поля с данными о расписании.
     */
    DaySchedule getDaySchedule(Respondent respondent, DayOfWeek dayOfWeek);
    /**
     * Возвращает объект расписания за неделю для выбраного университета.
     *
     * @param respondent объект класса с информацией о пользователе
     * @return Объект расписания, содержащий поля с данными о расписании.
     */
    List<DaySchedule> getSchedule(Respondent respondent);
}
