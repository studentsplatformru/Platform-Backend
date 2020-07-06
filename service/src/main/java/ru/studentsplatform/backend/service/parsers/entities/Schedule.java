package ru.studentsplatform.backend.service.parsers.entities;

public class Schedule {
    private final String title;
    private final String[] times;
    private final String[] disciplines;
    private final String[] locations;
    private final String[] educators;

    public Schedule(String title, String[] times, String[] disciplines, String[] locations, String[] educators) {
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
