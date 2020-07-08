package ru.studentsplatform.backend.service;

import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.enums.University;

import java.time.DayOfWeek;

public interface UniversityScheduleResolver {

    DaySchedule getSchedule(University university, String direction, String groupName, DayOfWeek dayOfWeek);
}
