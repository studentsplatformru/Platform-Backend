package ru.studentsplatform.backend.entities.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetTime;
import java.util.List;

/**
 * Класс конкретных занятий в университете (их распиание).
 */
@Entity
@Table(name = "lesson_unit")
public class LessonUnit extends BaseEntity {

    /** Поле начало занятия. */
    @Column(name = "start_time", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private OffsetTime startTime;

    /** Поле окончание занятия. */
    @Column(name = "end_time", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private OffsetTime endTime;

    /** Поле аудитория. */
    @Column(name = "audience", nullable = false)
    private String audience;

    /** Поле тип занятия. */
    @Column(name = "type", nullable = false)
    private String type;

    /** Поле примечание. */
    @Column(name = "note")
    private String note;

    /** Связь "один-ко-многим" - Занятие, для которого день уникален. */
    @OneToMany(mappedBy = "lessonUnit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    /** Связь "многие-к-одному" - Преподаватель. */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    /** Связь "многие-к-одному" - Предмет. */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public OffsetTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetTime startTime) {
        this.startTime = startTime;
    }

    public OffsetTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetTime endTime) {
        this.endTime = endTime;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}