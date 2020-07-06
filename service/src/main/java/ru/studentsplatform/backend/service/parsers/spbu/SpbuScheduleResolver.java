package ru.studentsplatform.backend.service.parsers.spbu;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.parsers.ScheduleParser;
import ru.studentsplatform.backend.service.parsers.UniversityScheduleResolver;

@Service
public class SpbuScheduleResolver implements UniversityScheduleResolver {

    private final ScheduleParser parser;
    private final SpbuScheduleFinder finder;

    public SpbuScheduleResolver(ScheduleParser parser,
                                SpbuScheduleFinder finder) {
        this.parser = parser;
        this.finder = finder;
    }


    @Override
    public String getSchedule(String keywords) {
        String[] separatedKeywords = keywords.split(";");
        String url = finder.findScheduleLink(separatedKeywords[0].trim(), separatedKeywords[1].trim());
        try {
            parser.setConnection(url);
        } catch (IllegalArgumentException e) {
            return "Не могу найти расписание по этим данным :(";
        }

        return parser.getDailySchedule(separatedKeywords[2].trim()).toString();
    }
 //TODO на данный момент метод парсера возвращает объект, а не строку.


}
