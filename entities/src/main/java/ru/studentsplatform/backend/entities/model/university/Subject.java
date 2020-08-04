package ru.studentsplatform.backend.entities.model.university;

import ru.studentsplatform.backend.entities.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Класс предметов в университете.
 */
@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {
	@Column(name = "subject_name", nullable = false)
	private String subjectName;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
