package ru.studentsplatform.backend.service.parsers.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DayScheduleTest {
    DaySchedule daySchedule = new DaySchedule("Monday",
            Arrays.asList("13", "14", "51"),
            Arrays.asList("афыва", "фвыа", "афыва"),
            Arrays.asList("фыва", "пфывп", "роеыапр"),
            Arrays.asList("ичьыен", "пыаврйк", "пфывп"));


    @Test
    void testToStringMethod() {
        Assertions.assertEquals("Monday\n" +
                "13; афыва; фыва; ичьыен\n" +
                "14; фвыа; пфывп; пыаврйк\n" +
                "51; афыва; роеыапр; пфывп\n", daySchedule.toString());
    }
}
