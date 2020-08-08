package ru.studentsplatform.backend.entities.model.university;

import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "task")
public class Task extends BaseEntity {

	@Column(name = "task_name", nullable = false)
	private String taskName;

	@Column(name = "dead_line")
	private OffsetDateTime deadLine;

	@Column(name = "is_done", nullable = false)
	private Boolean isDone;

	@Column(name = "mark")
	private Integer mark;

	@ManyToOne(fetch = FetchType.LAZY)
	private ScheduleUserCell scheduleUserCell;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	private List<TaskAttachment> attachments;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public OffsetDateTime getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(OffsetDateTime deadLine) {
		this.deadLine = deadLine;
	}

	public Boolean getDone() {
		return isDone;
	}

	public void setDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public ScheduleUserCell getScheduleUserCell() {
		return scheduleUserCell;
	}

	public void setScheduleUserCell(ScheduleUserCell scheduleUserCell) {
		this.scheduleUserCell = scheduleUserCell;
	}

	public List<TaskAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<TaskAttachment> attachments) {
		this.attachments = attachments;
	}
}
