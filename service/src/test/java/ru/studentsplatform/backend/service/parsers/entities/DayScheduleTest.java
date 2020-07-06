package ru.studentsplatform.backend.service.parsers.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DayScheduleTest {
    DaySchedule daySchedule = new DaySchedule("Monday",
            new String[]{"13", "14", "51"},
            new String[]{"афыва", "фвыа", "афыва"},
            new String[]{"фыва", "пфывп", "роеыапр"},
            new String[]{"ичьыен", "пыаврйк", "пфывп"});


    @Test
    void testToStringMethod() {
        Assertions.assertEquals("Monday\n" +
                "13; афыва; фыва; ичьыен\n" +
                "14; фвыа; пфывп; пыаврйк\n" +
                "51; афыва; роеыапр; пфывп\n", daySchedule.toString());
    }
}
