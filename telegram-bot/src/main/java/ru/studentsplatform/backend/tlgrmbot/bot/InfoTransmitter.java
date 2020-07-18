package ru.studentsplatform.backend.tlgrmbot.bot;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.UniversityResolverFactory;
import ru.studentsplatform.backend.service.UniversityScheduleResolver;
import ru.studentsplatform.backend.service.entities.Respondent;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.enums.University;

import java.util.List;

@Service
public class InfoTransmitter {
    private final UniversityResolverFactory factory;

    public InfoTransmitter(UniversityResolverFactory factory) {
        this.factory = factory;
    }

    public List<DaySchedule> getScheduleById(int userId) {
        //заглушка - начало
        Respondent respondent = new Respondent(); //TODO залезаем в бд и достаём информацию по userId
        respondent.setDirection("Mahematics and Computer Science");
        respondent.setUniversity(University.SPBU);
        respondent.setGroupName("19.Б03-мкн");
        //заглушка - конец

        List<DaySchedule> schedules = null;//TODO залезаем в другую бд и доастём расписание по информации


        if (schedules == null) {
            schedules = createWeekSchedule(respondent);
        }
        return schedules;
    }

    private List<DaySchedule> createWeekSchedule(Respondent info) {
        UniversityScheduleResolver resolver = factory.getResolver(info.getUniversity());
        return resolver.getSchedule(info);
    }
}
