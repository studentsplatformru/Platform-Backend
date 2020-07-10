package ru.studentsplatform.backend.service.spbu.finder;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpbuScheduleUrlFinderTest {
    @Autowired
    private ScheduleUrlFinder scheduleFinder;

    @Test
    public void getLinkByGroupNameTest() {
        String Url = scheduleFinder.findScheduleLink("Biology", "19.Б01-Б");
        assertEquals(Url, "https://timetable.spbu.ru/BIOL/StudentGroupEvents/Primary/247986");
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongDataResponseTest() {
        String response = scheduleFinder.findScheduleLink("someString", "someString");
        assertEquals(response, "URL not found!");
    }

    @Configuration
    static class ServiceSpringTestConfiguration {
        @Bean()
        public ScheduleUrlFinder getFinder() {
            return new SpbuScheduleUrlFinder();
        }
    }

}
