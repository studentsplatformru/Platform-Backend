package ru.studentsplatform.backend.entities.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "lesson")
public class Lesson extends BaseEntity{

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
    private Set<Homework> homeworkSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
    private Set<Mark> marks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
    private Set<Attendance> attendanceSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_unit_id")
    private LessonUnit lessonUnit;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Homework> getHomeworkSet() {
        return homeworkSet;
    }

    public void setHomeworkSet(Set<Homework> homeworkSet) {
        this.homeworkSet = homeworkSet;
    }

    public Set<Attendance> getAttendanceSet() {
        return attendanceSet;
    }

    public void setAttendanceSet(Set<Attendance> attendanceSet) {
        this.attendanceSet = attendanceSet;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LessonUnit getLessonUnit() {
        return lessonUnit;
    }

    public void setLessonUnit(LessonUnit lessonUnit) {
        this.lessonUnit = lessonUnit;
    }
}
