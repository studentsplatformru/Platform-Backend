package ru.studentsplatform.backend.entities.model.university;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "task")
public class Task extends BaseEntity {

	@Column(name = "task_name", nullable = false)
	private String taskName;

	@Column(name = "dead_line")
	private Date deadLine;

	@Column(name = "is_done", nullable = false)
	private boolean isDone;

	@Column(name = "mark")
	private Integer mark;

	@ManyToOne
	@JoinColumn(name = "schedule_user_cell_id", nullable = false)
	private ScheduleUserCell scheduleUserCell;

	@OneToMany
	private List<TaskAttachment> attachments;
}
