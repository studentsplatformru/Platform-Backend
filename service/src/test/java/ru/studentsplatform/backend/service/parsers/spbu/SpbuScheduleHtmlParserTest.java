package ru.studentsplatform.backend.service.parsers.spbu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.service.parsers.entities.DaySchedule;

public class SpbuScheduleHtmlParserTest {
    SpbuScheduleHtmlParser parser = new SpbuScheduleHtmlParser();
    DaySchedule daySchedule;

    @BeforeEach
    void setParser() {
        parser.setConnection("https://timetable.spbu.ru/MCSC/StudentGroupEvents/Primary/248162/2020-05-25");
    }

    @Test
    void testDailyScheduleTitleMustReturnCurrentString() {
        daySchedule = parser.getDailySchedule("Monday");
        Assertions.assertEquals(daySchedule.getTitle(), "Monday, May 25");
    }
}