package ru.studentsplatform.backend.entities.model.enums;

public enum UniversityEnum {
	SPBU("СПБГУ"),
	SPBSTU("СПБПУ"),
	ITMO("ИТМО");

	private String name;

	UniversityEnum(String name) {
		this.name = name;
	}

	/**
	 * Получаем университет из строки с его аббревиатурой на русском языке.
	 *
	 * @param name Строка с названием университета.
	 * @return Объект университета.
	 */
	public static UniversityEnum getUniversityByName(String name) {
		for (UniversityEnum university : UniversityEnum.values()) {
			if (university.getName().equals(name)) {
				return university;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}
}
