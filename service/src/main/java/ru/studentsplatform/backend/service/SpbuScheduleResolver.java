package ru.studentsplatform.backend.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.enums.University;
import ru.studentsplatform.backend.service.spbu.finder.SpbuScheduleUrlFinder;
import ru.studentsplatform.backend.service.spbu.parser.SpbuScheduleHtmlParser;

import java.time.DayOfWeek;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class SpbuScheduleResolver implements UniversityScheduleResolver {

    /**
     * Объект, который парсит страницу и возвращает объект расписания на день.
     */
    private final SpbuScheduleHtmlParser parser;
    /**
     * Объект, который находит на сайте конкретную страницу расписания.
     */
    private final SpbuScheduleUrlFinder finder;

    public SpbuScheduleResolver(SpbuScheduleHtmlParser parser, SpbuScheduleUrlFinder finder) {
        this.parser = parser;
        this.finder = finder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DaySchedule getDaySchedule(University university,
                                      String direction,
                                      String groupName,
                                      DayOfWeek dayOfWeek) {
        String url = finder.findScheduleLink(direction, groupName);

        return parser.getDaySchedule(dayOfWeek, url);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DaySchedule> getSchedule(University university,
                                         String direction,
                                         String groupName) {
        String url = finder.findScheduleLink(direction, groupName);

        return parser.getWeekSchedule(url);
    }
}
