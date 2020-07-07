package ru.studentsplatform.backend.service.parsers.entities;

import java.util.List;

public class DaySchedule {
    private final String title;
    private final List<String> times;
    private final List<String> disciplines;
    private final List<String> locations;
    private final List<String> educators;

    /**
     * Класс, представляющий объект распиания. Размер любого из массивов (например, times),
     * отражает количество занятий в этот день.
     *
     * @param title       хранит день недели, представленный объектом класса
     * @param times       хранит время каждой дисциплины в этот день
     * @param disciplines хранит название каждой дисциплины в этот день
     * @param locations   хранит местонахождение каждой дисциплины в этот день
     * @param educators   хранит фамилию и инициалы преподавателя каждой дисциплины в этот день
     */
    public DaySchedule(String title,
                       List<String> times,
                       List<String> disciplines,
                       List<String> locations,
                       List<String> educators) {
        this.title = title;
        this.times = times;
        this.disciplines = disciplines;
        this.locations = locations;
        this.educators = educators;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTimes() {
        return times;
    }

    public List<String> getDisciplines() {
        return disciplines;
    }

    public List<String> getLocations() {
        return locations;
    }

    public List<String> getEducators() {
        return educators;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(title).append("\n");

        for (int i = 0; i < times.size(); i++) {
            stringBuilder.append(times.get(i)).append("; ")
                    .append(disciplines.get(i)).append("; ")
                    .append(locations.get(i)).append("; ")
                    .append(educators.get(i)).append("\n");
        }
        return String.valueOf(stringBuilder);
    }
}
