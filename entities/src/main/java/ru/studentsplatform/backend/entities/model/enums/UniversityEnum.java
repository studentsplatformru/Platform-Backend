package ru.studentsplatform.backend.entities.model.enums;

public enum UniversityEnum {
	SPBU("Санкт-Петербургский государственный университет"),
	ITMO("Университет ИТМО");

	private String name;

	UniversityEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
