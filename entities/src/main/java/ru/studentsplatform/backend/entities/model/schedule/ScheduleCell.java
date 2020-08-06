package ru.studentsplatform.backend.entities.model.schedule;

import org.springframework.format.annotation.DateTimeFormat;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.enums.ClassTypeEnum;
import ru.studentsplatform.backend.entities.model.university.Subject;
import ru.studentsplatform.backend.entities.model.university.Team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "schedule_cell")
public class ScheduleCell extends BaseEntity {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private OffsetDateTime startClass;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private OffsetDateTime endClass;

	private Integer semester;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private ClassTypeEnum type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public OffsetDateTime getStartClass() {
		return startClass;
	}

	public void setStartClass(OffsetDateTime startClass) {
		this.startClass = startClass;
	}

	public OffsetDateTime getEndClass() {
		return endClass;
	}

	public void setEndClass(OffsetDateTime endClass) {
		this.endClass = endClass;
	}

	public ClassTypeEnum getType() {
		return type;
	}

	public void setType(ClassTypeEnum type) {
		this.type = type;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
