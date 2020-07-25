package ru.studentsplatform.backend.entities.model.university;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "place_study")
public class PlaceStudy extends BaseEntity {
	@OneToOne
	@MapsId
	private User user;

	@ManyToOne
	@JoinColumn(name = "university_id", nullable = false)
	private University university;

	@ManyToOne
	@JoinColumn(name = "faculty_id", nullable = false)
	private Faculty faculty;

	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@ManyToOne
	@JoinColumn(name = "direction_id", nullable = false)
	private Direction direction;

	@ManyToOne
	@JoinColumn(name = "team_id", nullable = false)
	private Team team;

	private int semester;
}
