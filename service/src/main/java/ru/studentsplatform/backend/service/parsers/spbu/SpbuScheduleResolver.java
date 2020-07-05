package ru.studentsplatform.backend.service.parsers.spbu;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.parsers.UniversityScheduleResolver;

@Service
public class SpbuScheduleResolver implements UniversityScheduleResolver {

    private final SpbuScheduleParser parser;
    private final SpbuScheduleFinder finder;

    public SpbuScheduleResolver(SpbuScheduleParser parser,
                                SpbuScheduleFinder finder){
        this.parser = parser;
        this.finder = finder;
    }

    @Override
    public String getSchedule(String... keyWords) {
        String url = finder.findScheduleLink(keyWords[1],keyWords[2]);
        parser.setConnection(url);
        return parser.getDailySchedule(keyWords[3]);
    }
}
