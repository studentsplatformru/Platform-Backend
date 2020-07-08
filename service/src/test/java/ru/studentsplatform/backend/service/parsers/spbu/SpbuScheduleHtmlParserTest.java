package ru.studentsplatform.backend.service.parsers.spbu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.service.parsers.entities.DaySchedule;

import java.time.DayOfWeek;

public class SpbuScheduleHtmlParserTest {
    SpbuScheduleHtmlParser parser = new SpbuScheduleHtmlParser();
    DaySchedule daySchedule;

    @Test
    void testDailyScheduleTitleMustReturnCurrentString() {
        daySchedule = parser.getDailySchedule(DayOfWeek.MONDAY,
                "https://timetable.spbu.ru/MCSC/StudentGroupEvents/Primary/248162/2020-05-25");
        Assertions.assertEquals(daySchedule.getTitle(), "Monday, May 25");
    }
}