package ru.studentsplatform.backend.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.enums.University;
import ru.studentsplatform.backend.service.spbu.finder.SpbuScheduleUrlFinder;
import ru.studentsplatform.backend.service.spbu.parser.SpbuScheduleHtmlParser;

/**
 * Класс фабрики, предоставляющий выбор между разными реализациями
 * интерфейсов ScheduleUrlFinder и ScheduleHtmlParser.
 */
@Service
public class UniversityResolverFactory {
    /**
     * Метод возвращает объект, объединяющий конкретные реализации парсера и файндера
     * в зависимости от входного параметра.
     *
     * @param university enum имя университета
     * @return resolver или null
     */
    public UniversityScheduleResolver getResolver(University university) {
        switch (university) {
            case SPBU:
                return new UniversityScheduleResolverImpl(
                        new SpbuScheduleHtmlParser(),
                        new SpbuScheduleUrlFinder()
                );
            case ITMO:
                return null;
            case SPBSTU:
                return null;
            default:
                return null;
        }
    }
}
