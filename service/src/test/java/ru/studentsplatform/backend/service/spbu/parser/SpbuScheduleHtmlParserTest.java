package ru.studentsplatform.backend.service.spbu.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class SpbuScheduleHtmlParserTest {
    SpbuScheduleHtmlParser parser;

    DaySchedule daySchedule;
    List<DaySchedule> weekSchedule;
    String rightUrl = "https://timetable.spbu.ru/MCSC/StudentGroupEvents/Primary/248162/2020-05-25";
    String wrongUrl = "https://wrong.url";

    @BeforeEach
    void setUp() {
        parser = new SpbuScheduleHtmlParser();
    }

    @Test
    void dayScheduleTitleShouldReturnCurrentString() {
        daySchedule = parser.getDaySchedule(DayOfWeek.FRIDAY,
                rightUrl);
        Assertions.assertEquals(daySchedule.getDayTitle()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH), "Friday");
    }

    @Test
    void getDayElementShouldThrowExceptionWithWrongDayOfWeek() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> daySchedule = parser.getDaySchedule(DayOfWeek.SATURDAY, rightUrl));
    }

    @Test()
    void getDayScheduleShouldThrowExceptionWithWrongUrl() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> daySchedule = parser.getDaySchedule(DayOfWeek.MONDAY, wrongUrl));
    }

    @Test
    void getWeekScheduleShouldWork() {
        weekSchedule = parser.getWeekSchedule(rightUrl);
        Assertions.assertFalse(weekSchedule.isEmpty());
    }
}