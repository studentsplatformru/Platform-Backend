package ru.studentsplatform.backend.entities.model.university;

import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.enums.UniversityEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Класс университета.
 */
@Entity
@Table(name = "university")
public class University extends BaseEntity {
	@Column(name = "university")
	@Enumerated(EnumType.STRING)
	private UniversityEnum university;

	@OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
	private List<Faculty> faculties;

	public UniversityEnum getUniversity() {
		return university;
	}

	public void setUniversity(UniversityEnum university) {
		this.university = university;
	}

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}
}
