package ru.studentsplatform.backend.entities.model.schedule;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.enums.UniversityRoleEnum;
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

@Data
@Entity
@Table(name = "schedule_user_cell")
public class ScheduleUserCell extends BaseEntity {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "urs_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_cell")
	private ScheduleCell scheduleCell;

	@Column(name = "university_role")
	@Enumerated(EnumType.STRING)
	private UniversityRoleEnum universityRole;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleUserCell")
	private List<Task> tasks;
}
