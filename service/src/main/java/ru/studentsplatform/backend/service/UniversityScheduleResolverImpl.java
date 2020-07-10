package ru.studentsplatform.backend.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.enums.University;
import ru.studentsplatform.backend.service.spbu.finder.ScheduleUrlFinder;
import ru.studentsplatform.backend.service.spbu.parser.ScheduleHtmlParser;

import java.time.DayOfWeek;

/**
 * Объединяет в себе функционал интерфейсов ScheduleHtmlParser и ScheduleUrlFinder.
 */
@Service
public class UniversityScheduleResolverImpl implements UniversityScheduleResolver {

    /**
     * Объект, который парсит страницу и возвращает объект расписания на день.
     */
    private final ScheduleHtmlParser parser;
    /**
     * Объект, который находит на сайте конкретную страницу расписания.
     */
    private final ScheduleUrlFinder finder;

    public UniversityScheduleResolverImpl(ScheduleHtmlParser parser, ScheduleUrlFinder finder) {
        this.parser = parser;
        this.finder = finder;
    }

    /**
     * Возвращает объект расписания для выбраного университета.
     *
     * @param university Университет, для которого необходимо получить расписание.
     * @param direction  Направление подготовки, для которого необходимо получить расписание.
     * @param groupName  Название группы, для которого необходимо получить расписание.
     * @param dayOfWeek  День недели, для которого необходимо получить расписание.
     * @return Объект расписания, содержащий поля с данными о расписании.
     */
    @Override
    public DaySchedule getSchedule(University university,
                                   String direction,
                                   String groupName,
                                   DayOfWeek dayOfWeek) {
        String url = finder.findScheduleLink(direction, groupName);

        return parser.getDaySchedule(dayOfWeek, url);
    }

}
