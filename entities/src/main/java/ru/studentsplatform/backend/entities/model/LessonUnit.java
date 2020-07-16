package ru.studentsplatform.backend.entities.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "lessonUnit")
public class LessonUnit {
    @Id
    @Column(name = "lessonUnit_name")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonUnitId;

    @Column(name = "start_time",nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private Time startTime;

    @Column(name = "end_time",nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private Time endTime;

    @Column(name = "audience",nullable = false)
    private String audience;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "note",nullable = false)
    private String note;

    public Long getLessonUnitId() {
        return lessonUnitId;
    }

    public void setLessonUnitId(Long lessonUnitId) {
        this.lessonUnitId = lessonUnitId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
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