package ru.studentsplatform.backend.entities.model.university;

import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Класс групп в университете.
 */
@Entity
@Table(name = "team")
public class Team extends BaseEntity {
	@Column(name = "semester")
	private Integer semester;

	@Column(name = "team_name")
	private String teamName;

	@OneToMany
	@JoinColumn(name = "team_id")
	private List<User> users;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "direction_id")
	private Direction direction;

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
