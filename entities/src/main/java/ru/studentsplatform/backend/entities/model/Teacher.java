package ru.studentsplatform.backend.entities.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "personal_page",nullable = false)
    private String personalPage;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<TeachersFeedback> tFeedbacks;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<LessonUnit> lessonUnits;

    public Set<LessonUnit> getLessonUnits() {
        return lessonUnits;
    }

    public void setLessonUnits(Set<LessonUnit> lessonUnits) {
        this.lessonUnits = lessonUnits;
    }

    public Set<TeachersFeedback> gettFeedbacks() {
        return tFeedbacks;
    }

    public void settFeedbacks(Set<TeachersFeedback> tFeedbacks) {
        this.tFeedbacks = tFeedbacks;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getPersonalPage() {
        return personalPage;
    }

    public void setPersonalPage(String personalPage) {
        this.personalPage = personalPage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
