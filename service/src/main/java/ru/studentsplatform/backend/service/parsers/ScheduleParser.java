package ru.studentsplatform.backend.service.parsers;

import ru.studentsplatform.backend.service.parsers.entities.DaySchedule;

public interface ScheduleParser {

    DaySchedule getDailySchedule(String currentDay);
    void setConnection(String url);
}
