package ru.studentsplatform.backend.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.enums.University;
import ru.studentsplatform.backend.service.spbu.finder.ScheduleUrlFinder;
import ru.studentsplatform.backend.service.spbu.parser.ScheduleHtmlParser;

import java.time.DayOfWeek;
import java.util.List;

/**
 * {@inheritDoc}
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
