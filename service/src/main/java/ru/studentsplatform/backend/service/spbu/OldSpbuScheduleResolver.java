package ru.studentsplatform.backend.service.spbu;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.Respondent;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.spbu.finder.SpbuScheduleUrlFinder;
import ru.studentsplatform.backend.service.spbu.parser.SpbuScheduleHtmlParser;

import java.time.DayOfWeek;
import java.util.List;

/**
 * {@inheritDoc}.
 */
@Service
public class OldSpbuScheduleResolver implements OldUniversityScheduleResolver {

    /**
     * Парсит страницу и возвращает объект расписания на день.
     */
    private final SpbuScheduleHtmlParser parser;
    /**
     * Находит на сайте конкретную страницу расписания.
     */
    private final SpbuScheduleUrlFinder finder;

    public OldSpbuScheduleResolver(SpbuScheduleHtmlParser parser, SpbuScheduleUrlFinder finder) {
        this.parser = parser;
        this.finder = finder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DaySchedule getDaySchedule(Respondent respondent, DayOfWeek dayOfWeek) {
        String url = finder.findScheduleLink(respondent.getDirection(),
                respondent.getGroupName());

        return parser.getDaySchedule(dayOfWeek, url);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DaySchedule> getSchedule(Respondent respondent) {
        String url = finder.findScheduleLink(respondent.getDirection(),
                respondent.getGroupName());

        return parser.getWeekSchedule(url);
    }
}
