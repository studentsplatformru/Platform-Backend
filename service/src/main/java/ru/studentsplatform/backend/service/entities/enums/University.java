package ru.studentsplatform.backend.service.entities.enums;

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

    /**
     * Получаем университет из строки с его аббревиатурой на русском языке.
     *
     * @param universityName Строка с названием университета.
     * @return Объект университета.
     */
    public static University getUniversityByName(String universityName) {
        for (University university : University.values()) {
            if (university.getName().equals(universityName)) {
                return university;
            }
        }
        return null;
    }
}
