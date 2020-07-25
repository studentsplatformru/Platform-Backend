package ru.studentsplatform.backend.entities.model.university;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "discipline")
public class Discipline extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "usr_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;
}
