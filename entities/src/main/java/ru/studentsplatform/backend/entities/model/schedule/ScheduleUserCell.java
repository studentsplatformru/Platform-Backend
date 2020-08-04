package ru.studentsplatform.backend.entities.model.schedule;

import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.enums.UniversityRoleEnum;
import ru.studentsplatform.backend.entities.model.university.Discipline;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "schedule_user_cell")
public class ScheduleUserCell extends BaseEntity {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discipline_id")
	private Discipline discipline;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_cell")
	private ScheduleCell scheduleCell;

	@Column(name = "university_role")
	@Enumerated(EnumType.STRING)
	private UniversityRoleEnum universityRole;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleUserCell")
	private List<Task> tasks;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public ScheduleCell getScheduleCell() {
		return scheduleCell;
	}

	public void setScheduleCell(ScheduleCell scheduleCell) {
		this.scheduleCell = scheduleCell;
	}

	public UniversityRoleEnum getUniversityRole() {
		return universityRole;
	}

	public void setUniversityRole(UniversityRoleEnum universityRole) {
		this.universityRole = universityRole;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
