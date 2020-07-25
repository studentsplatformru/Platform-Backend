package ru.studentsplatform.backend.entities.model.university;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "task")
public class Task extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "schedule_user_cell_id", nullable = false)
	private ScheduleUserCell scheduleUserCell;
}
