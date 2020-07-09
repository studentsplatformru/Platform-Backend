package ru.studentsplatform.backend.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.enums.University;

import java.time.DayOfWeek;

@Service
public class UniversityScheduleResolverImpl implements UniversityScheduleResolver {

    private final UniversityScheduleServiceFactory factory;

    public UniversityScheduleResolverImpl(UniversityScheduleServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    public DaySchedule getSchedule(University university, String direction, String groupName, DayOfWeek dayOfWeek) {
        return factory.getParser(university).getDaySchedule(
                dayOfWeek,factory.getFinder(university).findScheduleLink(direction,groupName));
    }

}
