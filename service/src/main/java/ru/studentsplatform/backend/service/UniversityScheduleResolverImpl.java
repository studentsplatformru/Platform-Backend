package ru.studentsplatform.backend.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.enums.University;

import java.time.DayOfWeek;

/**
 * Объединяет в себе функционал интерфейсов ScheduleHtmlParser и ScheduleUrlFinder
 * Использует класс фабрики для выбора их реализции на основе первого параметра,
 * передающегося методу интерфейса, который данный класс реализует.
 */
@Service
public class UniversityScheduleResolverImpl implements UniversityScheduleResolver {

    private final UniversityScheduleServiceFactory factory;

    public UniversityScheduleResolverImpl(UniversityScheduleServiceFactory factory) {
        this.factory = factory;
    }

    /**
     * Возвращает объект расписания для выбраного университета.
     * @param university Университет, для которого необходимо получить расписание.
     * @param direction Направление подготовки, для которого необходимо получить расписание.
     * @param groupName Название группы, для которого необходимо получить расписание.
     * @param dayOfWeek День недели, для которого необходимо получить расписание.
     * @return Объект расписания, содержащий поля с данными о расписании.
     */
    @Override
    public DaySchedule getSchedule(University university, String direction, String groupName, DayOfWeek dayOfWeek) {
        return factory.getParser(university).getDaySchedule(
                dayOfWeek, factory.getFinder(university).findScheduleLink(direction, groupName));
    }

}
