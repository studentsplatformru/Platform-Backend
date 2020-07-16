package ru.studentsplatform.backend.entities.model;

import javax.persistence.*;

@Entity
@Table(name = "homework")
public class Homework {
    @Id
    @Column(name = "homework_name")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long homeworkId;

    @Column(name = "homework_for_students",nullable = false)
    private String homeworkForStudents;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkForStudents() {
        return homeworkForStudents;
    }

    public void setHomeworkForStudents(String homeworkForStudents) {
        this.homeworkForStudents = homeworkForStudents;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
