package ru.studentsplatform.backend.service;

import ru.studentsplatform.backend.service.entities.Respondent;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;

import java.time.DayOfWeek;
import java.util.List;

public class NewSpbuScheduleResolver implements UniversityScheduleResolver {
    @Override
    public DaySchedule getDaySchedule(Respondent respondent, DayOfWeek dayOfWeek) {
        return null;
    }

    @Override
    public List<DaySchedule> getSchedule(Respondent respondent) {
        return null;
    }
}
