package ru.studentsplatform.backend.entities.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "student_id")
    private Long studentsId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_name",nullable = false)
    private Group group;

    /*
    TODO оценки
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Mark> marks;

    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }
     */

    /*
    TODO предметы
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
     */

    public Student(Group group) {
        this.group = group;
    }

    public Long getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(Long studentsId) {
        this.studentsId = studentsId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
