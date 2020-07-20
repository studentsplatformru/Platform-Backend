package ru.studentsplatform.backend.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Класс домашних заданий для студентов.
 */
@Entity
@Table(name = "homework")
public class Homework extends BaseEntity {

	/**
	 * Связь "один-ко-многим" - Занятие, для которого день уникален.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;

	// draft версия хранения дз

	/**
	 * Поле тип файла.
	 */
	@Column(name = "file_type", nullable = false)
	private String fileType;

	/**
	 * Поле файла.
	 */
	@Column(name = "file", nullable = false)
	private Byte[] file;

	/**
	 * Поле примечание.
	 */
	@Column(name = "note")
	private String note;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Byte[] getFile() {
		return file;
	}

	public void setFile(Byte[] file) {
		this.file = file;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
}
