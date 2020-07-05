package ru.studentsplatform.backend.service.parsers;

import java.util.LinkedList;

public interface UniversityScheduleResolver {

    String getSchedule(LinkedList<String> keyWords);
}
