package ru.studentsplatform.backend.service.schedule;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.studentsplatform.backend.service.schedule.entities.Respondent;
import ru.studentsplatform.backend.service.schedule.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.schedule.entities.enums.University;
import ru.studentsplatform.backend.service.schedule.spbu_new.RequestSender;
import ru.studentsplatform.backend.service.schedule.spbu_new.ScheduleUtils;
import ru.studentsplatform.backend.service.schedule.spbu_new.SpbuScheduleResolver;
import ru.studentsplatform.backend.service.schedule.spbu_new.UniversityScheduleResolver;


import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
public class UniversityResolverFactoryTest {
    @Autowired
    private UniversityResolverFactory factory;

    private static final Logger logger = LogManager.getLogger(UniversityResolverFactoryTest.class);

    @Test
    void createSpbuResolverTest() {
        UniversityScheduleResolver resolver = factory.getResolver(University.SPBU);
        Assertions.assertNotNull(resolver);
    }
    @Test
    void requestWeekSchedule() {
        UniversityScheduleResolver resolver = factory.getResolver(University.SPBU);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate firstDay = LocalDate.parse("2020-03-16", formatter);
        LocalDate lastDay = LocalDate.parse("2020-03-23", formatter);
        Respondent respondent = new Respondent();
        respondent.setGroupId(248949);
        respondent.setDate(firstDay.plusDays(1));
        List<DaySchedule> result1 = resolver.getSchedule(respondent, firstDay, lastDay);
        List<DaySchedule> result2 = resolver.getSchedule(respondent);
        Assertions.assertEquals(result1.toString(), result2.toString());

        StringBuilder sb = new StringBuilder();
        for (DaySchedule d : result1) {
            sb.append(d.toString()).append("\n");
        }
        logger.log(Level.INFO, new String(sb.toString().getBytes(StandardCharsets.UTF_8)));
    }

    @Configuration
    static class ServiceSpringTestConfiguration {
        @Bean()
        public ScheduleUtils getScheduleUtils() {
            return new ScheduleUtils("yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss");
        }
        @Bean()
        public RequestSender getRequestSender() {
            return new RequestSender("https://timetable.spbu.ru", getScheduleUtils());
        }
        @Bean()
        public SpbuScheduleResolver getSpbuScheduleResolver() {
            return new SpbuScheduleResolver(getRequestSender(), getScheduleUtils());
        }
        @Bean()
        public UniversityResolverFactory getUniversityResolverFactory() {
            return new UniversityResolverFactory(getSpbuScheduleResolver());
        }
    }
}
