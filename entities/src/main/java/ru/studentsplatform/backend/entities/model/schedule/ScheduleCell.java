package ru.studentsplatform.backend.entities.model.schedule;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.enums.ClassTypeEnum;
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

@Data
@Entity
@Table(name = "schedule_cell")
public class ScheduleCell extends BaseEntity {

	private OffsetDateTime startClass;

	private OffsetDateTime endClass;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private ClassTypeEnum type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
}
