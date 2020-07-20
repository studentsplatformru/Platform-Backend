package ru.studentsplatform.backend.service.entities.Schedule;

import java.time.LocalTime;

/**
 * Класс, представляющий объект расписания урока.
 */
public class Lesson {
    private LocalTime lessonStartTime;
    private LocalTime lessonEndTime;
    private String discipline;
    private String location;
    private String teacher;


    /**
     * Конструктор.
     *
     * @param lessonStartTime время начала урока
     * @param lessonEndTime   время конца урока
     * @param discipline      название урока
     * @param location        место проведения урока
     * @param teacher         имя преподавателя
     */
    public Lesson(LocalTime lessonStartTime,
                  LocalTime lessonEndTime,
                  String discipline,
                  String location,
                  String teacher) {
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
        this.discipline = discipline;
        this.location = location;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return String.format("Lesson:\n\t%s-%s\n\t%s\n\t%s\n\t%s",
                lessonStartTime,
                lessonEndTime,
                discipline,
                location,
                teacher);
    }
}
