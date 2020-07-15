package ru.studentsplatform.backend.entities.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_name")
    private Long groupId;

    @Column(name = "course",nullable = false)
    private int course;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "group")
    private Set<Student> students;

    /*
    TODO пары
    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "group")
    private Set<Lesson> lessons;
    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
     */

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }


    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
