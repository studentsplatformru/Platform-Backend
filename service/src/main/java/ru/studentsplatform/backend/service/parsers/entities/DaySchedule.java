package ru.studentsplatform.backend.service.parsers.entities;

public class DaySchedule {
    private final String title;
    private final String[] times;
    private final String[] disciplines;
    private final String[] locations;
    private final String[] educators;

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
    public DaySchedule(String title, String[] times, String[] disciplines, String[] locations, String[] educators) {
        this.title = title;
        this.times = times;
        this.disciplines = disciplines;
        this.locations = locations;
        this.educators = educators;
    }

    public String getTitle() {
        return title;
    }

    public String[] getTimes() {
        return times;
    }

    public String[] getDisciplines() {
        return disciplines;
    }

    public String[] getLocations() {
        return locations;
    }

    public String[] getEducators() {
        return educators;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(title).append("\n");

        for (int i = 0; i < times.length; i++) {
            stringBuilder.append(times[i]).append("; ")
                    .append(disciplines[i]).append("; ")
                    .append(locations[i]).append("; ")
                    .append(educators[i]).append("\n");
        }
        return String.valueOf(stringBuilder);
    }
}
