package ru.studentsplatform.backend.entities.model.enums;

public enum ClassTypeEnum {
	LECTURE("Лекция"),
	PRACTICE("Практика"),
	TEST("Зачет"),
	EXAM("Экзамен");

	private String type;

	ClassTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
