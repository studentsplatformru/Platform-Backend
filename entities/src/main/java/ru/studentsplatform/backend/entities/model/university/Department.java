package ru.studentsplatform.backend.entities.model.university;

import ru.studentsplatform.backend.entities.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Кафедра в университете.
 */
@Entity
@Table(name = "department")
public class Department extends BaseEntity {
	@Column(name = "department", nullable = false)
	private String department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
}