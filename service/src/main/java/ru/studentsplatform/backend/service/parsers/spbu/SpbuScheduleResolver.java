package ru.studentsplatform.backend.service.parsers.spbu;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.parsers.ScheduleParser;

@Service
public class SpbuScheduleResolver {

    private final ScheduleParser parser;
    private final SpbuScheduleFinder finder;

    public SpbuScheduleResolver(ScheduleParser parser,
                                SpbuScheduleFinder finder) {
        this.parser = parser;
        this.finder = finder;
    }

/*
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
*/ //TODO на данный момент метод парсера возвращает объект, а не строку.


}
