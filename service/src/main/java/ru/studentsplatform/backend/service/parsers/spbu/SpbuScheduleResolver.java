package ru.studentsplatform.backend.service.parsers.spbu;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.parsers.ScheduleFinder;
import ru.studentsplatform.backend.service.parsers.ScheduleHtmlParser;
import ru.studentsplatform.backend.service.parsers.UniversityScheduleResolver;

import java.time.DayOfWeek;


@Service
public class SpbuScheduleResolver implements UniversityScheduleResolver {

    private final ScheduleHtmlParser parser;
    private final ScheduleFinder finder;

    public SpbuScheduleResolver(ScheduleHtmlParser parser,
                                ScheduleFinder finder) {
        this.parser = parser;
        this.finder = finder;
    }


    @Override
    public String getSchedule(String keywords) {
        String[] separatedKeywords = keywords.split(";");
        String url = finder.findScheduleLink(separatedKeywords[0].trim(), separatedKeywords[1].trim());

        return parser.getDaySchedule(DayOfWeek.FRIDAY, url).toString();
    }
    //TODO на данный момент метод парсера возвращает объект, а не строку.
}
