package ru.studentsplatform.backend.service.entities.Schedule;

/**
 * Класс, представляющий объект расписания урока.
 */
public class Lesson {
    private String time;
    private String discipline;
    private String location;
    private String teacher;

    /**
     * Конструктор.
     *
     * @param time       временной интервал, в который проходит урок
     * @param discipline название урока
     * @param location   место проведения урока
     * @param teacher    имя преподавателя
     */
    public Lesson(String time, String discipline, String location, String teacher) {
        this.time = time; //TODO вот тут должен быть объект а не строка
        this.discipline = discipline;
        this.location = location;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "time='" + time + '\'' +
                ", discipline='" + discipline + '\'' +
                ", location='" + location + '\'' +
                ", teacher='" + teacher + '\'' +
                '}'; //TODO можно сделать лучше?
    }
}
