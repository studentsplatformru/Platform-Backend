package ru.studentsplatform.backend.entities.model.user;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.enums.UniversityRoleEnum;
import ru.studentsplatform.backend.entities.model.university.Discipline;
import ru.studentsplatform.backend.entities.model.university.PlaceStudy;
import ru.studentsplatform.backend.entities.model.university.Team;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Класс всех пользователей.
 */
@Data
@Entity
@Table(name = "usr")
public class User extends BaseEntity {
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserInfo userInfo;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "telegram_id")
	private String telegram_id;

	@Column(name = "vk_id")
	private String vk_id;

	@Column(name = "notification_type",
			columnDefinition = "varchar(255) default EMAIL")
	@Enumerated(EnumType.STRING)
	private NotificationType notification_type;

	@Column(name = "university_role")
	@Enumerated(EnumType.STRING)
	private UniversityRoleEnum universityRole;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private PlaceStudy placeStudy;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Discipline> disciplines;
}
