package ru.studentsplatform.backend.service.parsers.spbu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.service.parsers.entities.Schedule.DaySchedule;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class SpbuScheduleHtmlParserTest {
    SpbuScheduleHtmlParser parser = new SpbuScheduleHtmlParser();
    DaySchedule daySchedule;

    @Test
    void testDailyScheduleTitleMustReturnCurrentString() {
        daySchedule = parser.getDaySchedule(DayOfWeek.MONDAY,
                "https://timetable.spbu.ru/MCSC/StudentGroupEvents/Primary/248162/2020-05-25");
        Assertions.assertEquals(daySchedule.getDayTitle()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH), "Monday");
    }
}