package ru.studentsplatform.backend.entities.model.user;

import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.enums.UniversityRoleEnum;
import ru.studentsplatform.backend.entities.model.university.Discipline;
import ru.studentsplatform.backend.entities.model.university.PlaceStudy;
import ru.studentsplatform.backend.entities.model.university.Team;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Класс всех пользователей.
 */
@Entity
@Table(name = "usr")
public class User extends BaseEntity {
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserInfo userInfo;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UniversityRoleEnum getUniversityRole() {
		return universityRole;
	}

	public void setUniversityRole(UniversityRoleEnum universityRole) {
		this.universityRole = universityRole;
	}

	public PlaceStudy getPlaceStudy() {
		return placeStudy;
	}

	public void setPlaceStudy(PlaceStudy placeStudy) {
		this.placeStudy = placeStudy;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}
}
