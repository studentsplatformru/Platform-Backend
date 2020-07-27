package ru.studentsplatform.backend.entities.model.utility;

import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.university.Task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Приложение к Task.
 */
@Entity
@Table(name = "task_attachment")
public class TaskAttachment extends BaseEntity {

	@Column(name = "file_name")
	private String fileName;

	@Lob
	@Column(name = "content")
	private byte[] content;

	@ManyToOne(fetch = FetchType.LAZY)
	private Task task;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
