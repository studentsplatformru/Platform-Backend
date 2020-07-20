package ru.studentsplatform.backend.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.schedule.entities.enums.University;
import ru.studentsplatform.backend.service.schedule.spbu_new.SpbuScheduleResolver;
import ru.studentsplatform.backend.service.schedule.spbu_new.UniversityScheduleResolver;

/**
 * Класс фабрики, предоставляющий выбор между разными реализациями
 * интерфейсов ScheduleUrlFinder и ScheduleHtmlParser.
 */
@Service
public class UniversityResolverFactory {

    private final SpbuScheduleResolver spbuResolver;

    @Autowired
    public UniversityResolverFactory(SpbuScheduleResolver spbuResolver) {
        this.spbuResolver = spbuResolver;
    }

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
                return spbuResolver;
            case ITMO:
                return null;
            case SPBSTU:
                return null;
            default:
                return null;
        }
    }
}
