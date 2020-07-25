package ru.studentsplatform.backend.entities.model.university;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Класс предметов в университете.
 */
@Data
@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {
	@Column(name = "subject_name", nullable = false)
	private String subjectName;
}
