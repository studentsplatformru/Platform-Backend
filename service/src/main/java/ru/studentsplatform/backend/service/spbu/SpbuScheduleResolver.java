package ru.studentsplatform.backend.service.spbu;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.ScheduleHtmlParser;
import ru.studentsplatform.backend.service.ScheduleFinder;
import ru.studentsplatform.backend.service.UniversityScheduleResolver;

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
