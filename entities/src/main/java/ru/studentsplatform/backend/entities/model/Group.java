package ru.studentsplatform.backend.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Класс групп в университете
 */
@Entity
@Table(name = "group")
public class Group extends BaseEntity {

    /** Поле курс */
    @Column(name = "course", nullable = false)
    private int course;

    /** Поле название группы */
    @Column(name = "group_name", nullable = false)
    private int groupName;

    /** Связь "один-ко-многим" - Студент */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<Student> students;

    /** Связь "один-ко-многим" - Занятие, для которого день уникален */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<Lesson> lessons;

    /** Связь "один-ко-многим" - Предмет */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<Subject> subjects;

    /** Связь "многие-к-одному" - Направление*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direction_id")
    private Direction direction;

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroupName() {
        return groupName;
    }

    public void setGroupName(int groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
