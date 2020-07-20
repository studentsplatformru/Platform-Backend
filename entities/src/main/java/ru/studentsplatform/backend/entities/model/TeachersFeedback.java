package ru.studentsplatform.backend.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Класс обратной связи с преподавателями.
 */
@Entity
@Table(name = "teachers_feedback")
public class TeachersFeedback extends BaseEntity {

	/**
	 * Поле заголовок.
	 */
	@Column(name = "header", nullable = false)
	private String header;

	/**
	 * Поле содержимое.
	 */
	@Column(name = "content", nullable = false)
	private String content;

	/**
	 * Связь "многие-к-одному" - Пользователь.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private User author;

	/**
	 * Связь "многие-к-одному" - Преподаватель.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
