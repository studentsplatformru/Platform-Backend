package ru.studentsplatform.backend.service.parsers;

public interface ScheduleParser {

    String getDailySchedule(String currentDay);
    void setConnection(String url);
}
