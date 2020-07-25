package ru.studentsplatform.backend.entities.model.university;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Факультет в университете.
 */
@Data
@Entity
@Table(name = "faculty")
public class Faculty extends BaseEntity {
	@Column(name = "faculty_name", nullable = false)
	private String faculty;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_name")
	private University university;

	@OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
	private List<Department> departments;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
	private List<Direction> directions;
}
