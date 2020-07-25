package ru.studentsplatform.backend.entities.model.university;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.enums.UniversityEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Класс университета.
 */
@Data
@Entity
@Table(name = "university")
public class University extends BaseEntity {
	@Column(name = "university")
	@Enumerated(EnumType.STRING)
	private UniversityEnum university;

	@OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
	private List<Faculty> faculties;
}
