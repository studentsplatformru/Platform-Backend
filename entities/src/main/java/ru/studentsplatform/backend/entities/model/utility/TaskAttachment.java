package ru.studentsplatform.backend.entities.model.utility;

import lombok.Data;
import ru.studentsplatform.backend.entities.model.BaseEntity;
import ru.studentsplatform.backend.entities.model.university.Task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


@Data
@Entity
@Table(name = "task_attachment")

public class TaskAttachment extends BaseEntity {

    @Column(name = "file_name")
    private String fileName;

    @Lob
    @Column(name = "content", columnDefinition = "bytea")
    private byte[] fileContent;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}
