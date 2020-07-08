package ru.studentsplatform.backend.service.parsers.entities.enums;

public enum University {
    SPBU("СПБГУ"),
    SPBSTU("СПБПУ"),
    ITMO("ИТМО");

    private final String nameUniversity;

    University(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }

    public String getName() {
        return nameUniversity;
    }
}
