package ru.studentsplatform.backend.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Класс направлений подготовки в университете.
 */
@Entity
@Table(name = "direction")
public class Direction extends BaseEntity {

	/**
	 * Поле название направления подготовки.
	 */
	@Column(name = "direction_name", nullable = false)
	private String directionName;

	/**
	 * Поле код напрвления подготовки.
	 */
	@Column(name = "direction_code", nullable = false)
	private String directionCode;

	/**
	 * Связь "многие-к-одному" - Факультет.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;

	/**
	 * Связь "один-ко-многим" - Группа.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "direction")
	private Set<Team> teams;

	public String getDirectionName() {
		return directionName;
	}

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
}