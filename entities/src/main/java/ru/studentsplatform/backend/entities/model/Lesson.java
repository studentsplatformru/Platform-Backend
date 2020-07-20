package ru.studentsplatform.backend.entities.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Класс занятий в универеситете, для которых день уникален.
 */
@Entity
@Table(name = "lesson")
public class Lesson extends BaseEntity {

	/**
	 * Поле дата занятия.
	 */
	@Column(name = "date", nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private OffsetDateTime date;

	/**
	 * Связь "один-ко-многим" - Домашнее задание.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
	private List<Homework> homeworkList;

	/**
	 * Связь "один-ко-многим" - Оценка.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
	private List<Mark> marks;

	/**
	 * Связь "один-ко-многим" - Посещаемость.
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
	private List<Attendance> attendanceList;

	/**
	 * Связь "многие-к-одному" - Группа.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Team team;

	/**
	 * Связь "многие-к-одному" - Конкретная пара (её расписание).
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lesson_unit_id")
	private LessonUnit lessonUnit;

	public OffsetDateTime getDate() {
		return date;
	}

	public void setDate(OffsetDateTime date) {
		this.date = date;
	}

	public List<Homework> getHomeworkList() {
		return homeworkList;
	}

	public void setHomeworkList(List<Homework> homeworkList) {
		this.homeworkList = homeworkList;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public LessonUnit getLessonUnit() {
		return lessonUnit;
	}

	public void setLessonUnit(LessonUnit lessonUnit) {
		this.lessonUnit = lessonUnit;
	}
}
