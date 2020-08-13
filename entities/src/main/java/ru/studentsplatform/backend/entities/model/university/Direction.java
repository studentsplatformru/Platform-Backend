package ru.studentsplatform.backend.entities.model.university;

import ru.studentsplatform.backend.entities.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Направление.
 * <p>
 * Например: Прикладная математика и информатика
 * </p>
 */
@Entity
@Table(name = "direction")
public class Direction extends BaseEntity {

	/**
	 * Поле название направления подготовки.
	 */
	@Column(name = "direction")
	private String direction;

	/**
	 * Поле код напрвления подготовки.
	 */
	@Column(name = "direction_code")
	private String directionCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "direction")
	private List<Team> teams;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}