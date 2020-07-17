package ru.studentsplatform.backend.entities.model;

import javax.persistence.*;

@Entity
@Table(name = "homework")
public class Homework {
    @Id
    @Column(name = "homework_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long homeworkId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    // Хранение дз. Под вопросом...
    @Column(name = "file_name",nullable = false)
    private String fileName;

    @Column(name = "file_type",nullable = false)
    private String fileType;

    @Column(name = "file",nullable = false)
    private Byte[] file;

    @Column(name = "note")
    private String note;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
