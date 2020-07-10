package ru.studentsplatform.backend.service.spbu.finder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.service.spbu.finder.SpbuScheduleUrlFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpbuScheduleUrlFinderTest {
    private static SpbuScheduleUrlFinder scheduleFinder;

    @BeforeAll
    public static void initAll() {
        scheduleFinder = new SpbuScheduleUrlFinder();
    }

    @Test
    public void getLinkByGroupNameTest() {
        String Url = scheduleFinder.findScheduleLink("Biology", "19.Б01-Б");

        assertEquals(Url, "https://timetable.spbu.ru/BIOL/StudentGroupEvents/Primary/247986");
    }

    @Test
    public void wrongDataResponseTest() {
        String response = scheduleFinder.findScheduleLink("someString", "someString");
        assertEquals(response, "URL not found!");
    }
}
