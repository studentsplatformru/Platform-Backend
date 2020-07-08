package ru.studentsplatform.backend.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.enums.University;
import ru.studentsplatform.backend.service.spbu.SpbuScheduleHtmlParser;
import ru.studentsplatform.backend.service.spbu.SpbuScheduleUrlFinder;

import java.time.DayOfWeek;

@Service
public class UniversityScheduleResolverImpl implements UniversityScheduleResolver {

    private final SpbuScheduleHtmlParser spbuScheduleHtmlParser;
    private final SpbuScheduleUrlFinder spbuScheduleUrlFinder;

    public UniversityScheduleResolverImpl(SpbuScheduleHtmlParser spbuScheduleHtmlParser, SpbuScheduleUrlFinder spbuScheduleUrlFinder) {
        this.spbuScheduleHtmlParser = spbuScheduleHtmlParser;
        this.spbuScheduleUrlFinder = spbuScheduleUrlFinder;
    }

    @Override
    public DaySchedule getSchedule(University university, String direction, String groupName, DayOfWeek dayOfWeek) {
        switch (university){
            case SPBU:   return spbuScheduleHtmlParser.
                            getDaySchedule(dayOfWeek,spbuScheduleUrlFinder.findScheduleLink(direction,groupName));
            case ITMO:   return null;
            case SPBSTU: return null;
            default:     return null;
        }
    }
}
