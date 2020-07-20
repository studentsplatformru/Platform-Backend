package ru.studentsplatform.backend.service.schedule.spbu_new;

import org.jetbrains.annotations.NotNull;
import ru.studentsplatform.backend.service.schedule.entities.Respondent;
import ru.studentsplatform.backend.service.schedule.entities.Schedule.DaySchedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Объединяет в себе функционал интерфейсов ScheduleHtmlParser и ScheduleUrlFinder.
 */
public interface UniversityScheduleResolver {
    /**
     * Возвращает список объектов расписания за заданный промежуток для выбраного университета.
     * Если в заданный промежуток пар нет, то возвращается пустой массив.
     * Обязательно leftDay < rightDay.
     *
     * @param respondent объект класса с информацией о пользователе
     * @param leftDay    первый день интервала, для которого необходимо получить расписание
     * @param rightDay   последний день интервала, для которого необходимо получить расписание
     *                   (в интервал не включается)
     * @return Возвращает список объектов расписания за заданный промежуток для выбраного университета либо null,
     * если информация не получена
     */
    List<DaySchedule> getSchedule(@NotNull Respondent respondent, LocalDate leftDay, LocalDate rightDay);

    /**
     * Возвращает объект расписания за день для выбраного университета.
     *
     * @param respondent объект класса с информацией о пользователе
     * @param dayOfWeek  день недели, для которого необходимо получить расписание
     * @return объект расписания, содержащий поля с данными о расписании либо null, если информация не получена
     */
    DaySchedule getDaySchedule(@NotNull Respondent respondent, DayOfWeek dayOfWeek);

    /**
     * Возвращает объект расписания за неделю для выбраного университета.
     *
     * @param respondent объект класса с информацией о пользователе
     * @return объект расписания, содержащий поля с данными о расписании либо null, если информация не получена
     */
    List<DaySchedule> getSchedule(@NotNull Respondent respondent);
}
