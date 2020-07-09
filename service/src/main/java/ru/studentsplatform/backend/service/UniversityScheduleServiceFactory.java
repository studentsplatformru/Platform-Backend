package ru.studentsplatform.backend.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.enums.University;
import ru.studentsplatform.backend.service.spbu.SpbuScheduleHtmlParser;
import ru.studentsplatform.backend.service.spbu.SpbuScheduleUrlFinder;

@Service
public class UniversityScheduleServiceFactory {

    private final SpbuScheduleHtmlParser spbuScheduleHtmlParser;
    private final SpbuScheduleUrlFinder spbuScheduleUrlFinder;

    public UniversityScheduleServiceFactory(SpbuScheduleHtmlParser spbuScheduleHtmlParser,
                                            SpbuScheduleUrlFinder spbuScheduleUrlFinder) {
        this.spbuScheduleHtmlParser = spbuScheduleHtmlParser;
        this.spbuScheduleUrlFinder = spbuScheduleUrlFinder;
    }

    public ScheduleHtmlParser getParser(University university) {
        switch (university){
            case SPBU:   return spbuScheduleHtmlParser;
            case ITMO:   return null;
            case SPBSTU: return null;
            default:     return null;
        }
    }

    public ScheduleUrlFinder getFinder(University university) {
        switch (university){
            case SPBU:   return spbuScheduleUrlFinder;
            case ITMO:   return null;
            case SPBSTU: return null;
            default:     return null;
        }
    }

}
