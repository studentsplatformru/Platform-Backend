package ru.studentsplatform.backend.service.parsers.entities;

import org.junit.jupiter.api.Test;

public class ScheduleTest {
    Schedule schedule = new Schedule("Monday", new String[]{"13", "14", "51"}, new String[]{"афыва", "фвыа", "афыва"}, new String[]{"фыва", "пфывп", "роеыапр"}, new String[]{"ичьыен", "пыаврйк", "пфывп"});


    @Test
    void testToStringMethod(){
        System.out.println(schedule);
    }
}
