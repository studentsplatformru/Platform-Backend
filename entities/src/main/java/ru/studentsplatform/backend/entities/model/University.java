package ru.studentsplatform.backend.entities.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Класс университета.
 */
@Entity
@Table(name = "university", uniqueConstraints = {@UniqueConstraint(columnNames = "university_name")})
public class University extends BaseEntity implements Serializable {

	/**
	 * Поле название университета.
	 */

	@Column(name = "university_name")
	private String universityName;

	/**
	 * Связь "один-ко-многим" - Факультет.
	 */
	@OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
	private Set<Faculty> faculties;

	/**
	 * Связь "один-ко-многим" - Библиотека.
	 */
	@OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
	private List<Library> libraries;

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}

	public List<Library> getLibraries() {
		return libraries;
	}

	public void setLibraries(List<Library> libraries) {
		this.libraries = libraries;
	}
}
