package ru.studentsplatform.backend.service.parsers;

import ru.studentsplatform.backend.service.parsers.entities.Schedule;

public interface ScheduleParser {

    Schedule getDailySchedule(String currentDay);
    void setConnection(String url);
}
