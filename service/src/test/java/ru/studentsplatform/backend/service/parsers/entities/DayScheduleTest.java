package ru.studentsplatform.backend.service.parsers.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.service.parsers.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.parsers.entities.Schedule.Lesson;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class DayScheduleTest {
    DaySchedule daySchedule;


    @Test
    void testToStringMethod() {
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson(
                "1-4",
                "Philosophy",
                "Dom.street, 13",
                "Bob"
        ));
        daySchedule = new DaySchedule(DayOfWeek.MONDAY, lessons);


        System.out.println(daySchedule.toString());
        Assertions.assertEquals("Monday", daySchedule
                .getDayTitle()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    }
}
