package ru.studentsplatform.backend.entities.model.enums;

public enum UniversityRoleEnum {
	TEACHER("Преподаватель"),
	STUDENT("Студент"),
	ENTRANT("Абитуриент");

	private String role;

	UniversityRoleEnum(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}
