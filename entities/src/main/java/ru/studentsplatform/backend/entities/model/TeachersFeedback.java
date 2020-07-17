package ru.studentsplatform.backend.entities.model;

import javax.persistence.*;

@Entity
@Table(name = "teachers_feedback")
public class TeachersFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teachers_feedback_id")
    private Long teachersFeedbackId;

    @Column(name = "header",nullable = false)
    private String header;

    @Column(name = "content",nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Long getTeachersFeedbackId() {
        return teachersFeedbackId;
    }

    public void setTeachersFeedbackId(Long teachersFeedbackId) {
        this.teachersFeedbackId = teachersFeedbackId;
    }

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
