package ru.studentsplatform.backend.entities.model.university;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Кафедра в университете.
 */
@Data
@Entity
@Table(name = "department")
public class Department extends BaseEntity {

	/**
	 * Поле название кафедры.
	 */
	@Column(name = "department")
	private String department;

	/**
	 * Связь "многие-к-одному" - Факультет.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;

	@OneToMany
	@JoinColumn(name = "usr_id")
	private List<User> users;
}