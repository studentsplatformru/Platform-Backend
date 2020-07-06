package ru.studentsplatform.backend.service.parsers.spbu;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.parsers.UniversityScheduleResolver;
import java.util.LinkedList;

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
    public String getSchedule(LinkedList<String> dataList) {
        String url = finder.findScheduleLink(dataList.remove(),dataList.remove());
        try{
            parser.setConnection(url);
        } catch (IllegalArgumentException e){
            return "Не могу найти расписание по этим данным :(";
        }

        return parser.getDailySchedule(dataList.remove());
    }


}
