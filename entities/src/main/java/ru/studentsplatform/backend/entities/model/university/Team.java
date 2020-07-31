package ru.studentsplatform.backend.entities.model.university;

import lombok.Data;
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
@Data
@Entity
@Table(name = "team")
public class Team extends BaseEntity {
	@Column(name = "semester")
	private Long semester;

	@Column(name = "team_name")
	private String teamName;

	@OneToMany
	@JoinColumn(name = "team_id")
	private List<User> users;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "direction_id")
	private Direction direction;
}
