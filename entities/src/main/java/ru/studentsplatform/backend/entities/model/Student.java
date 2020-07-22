package ru.studentsplatform.backend.entities.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Класс студентов, обучающихся в университете.
 */
@Entity
@Table(name = "student")
public class Student extends BaseEntity {

	/**
	 * Связь "один-к-одному" - Пользователь.
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	@MapsId
	private User user;

	/**
	 * Связь "многие-к-одному" - кафедра.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	/**
	 * Связь "многие-к-одному" - Группа.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", nullable = false)
	private Team team;

	/**
	 * Связь "многие-к-одному" - Напрвление.
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "direction_id", nullable = true)
	private Direction direction;

	/**
	 * Связь "один-ко-многим" - Оценка.
	 */
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
	private List<Mark> marks;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
