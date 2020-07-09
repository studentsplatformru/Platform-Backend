package ru.studentsplatform.backend.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.enums.University;
import ru.studentsplatform.backend.service.spbu.SpbuScheduleHtmlParser;
import ru.studentsplatform.backend.service.spbu.SpbuScheduleUrlFinder;

/**
 * Класс фабрики, предоставляющий выбор между разными реализациями
 * интерфейсов ScheduleUrlFinder и ScheduleHtmlParser.
 */
@Service
public class UniversityScheduleServiceFactory {

    private final SpbuScheduleHtmlParser spbuScheduleHtmlParser;
    private final SpbuScheduleUrlFinder spbuScheduleUrlFinder;

    public UniversityScheduleServiceFactory(SpbuScheduleHtmlParser spbuScheduleHtmlParser,
                                            SpbuScheduleUrlFinder spbuScheduleUrlFinder) {
        this.spbuScheduleHtmlParser = spbuScheduleHtmlParser;
        this.spbuScheduleUrlFinder = spbuScheduleUrlFinder;
    }

    /**
     * Возвращает одну из реализаций ScheduleUrlFinder.
     * в зависимости от универстета.
     * @param university Enum, представляющий один из доступных к парсингу университетов.
     * @return Одна из реализаций ScheduleUrlFinder.
     */
    public ScheduleHtmlParser getParser(University university) {
        switch (university) {
            case SPBU: return spbuScheduleHtmlParser;
            case ITMO: return null;
            case SPBSTU: return null;
            default: return null;
        }
    }

    /**
     * Возвращает одну из реализаций ScheduleHtmlParser.
     * в зависимости от универстета.
     * @param university Enum, представляющий один из доступных к парсингу университетов.
     * @return Одна из реализаций ScheduleHtmlParser.
     */
    public ScheduleUrlFinder getFinder(University university) {
        switch (university) {
            case SPBU: return spbuScheduleUrlFinder;
            case ITMO: return null;
            case SPBSTU: return null;
            default: return null;
        }
    }

}
