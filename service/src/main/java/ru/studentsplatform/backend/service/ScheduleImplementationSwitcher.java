package ru.studentsplatform.backend.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.spbu.SpbuScheduleResolver;

@Service
public class ScheduleImplementationSwitcher {

    private final SpbuScheduleResolver spbuScheduleResolver;

    public ScheduleImplementationSwitcher(SpbuScheduleResolver spbuScheduleResolver) {
        this.spbuScheduleResolver = spbuScheduleResolver;
    }

    /**
     * Возвращает реализацию интерфейса UniversityScheduleResolver на основе параметра.
     *
     * @param universityName Сокращённое название университета, для которого будет производится поиск расписания.
     * @return UniversityScheduleResolver
     */
    public UniversityScheduleResolver switchRealisation(String universityName) {
        switch (universityName) {
            case "СПБГУ":
                return spbuScheduleResolver;
            default:
                return null;
        }
    }


}
