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
 * Класс кафедр, находящихся в университете.
 */
@Entity
@Table(name = "department")
public class Department extends BaseEntity {

    /** Поле название кафедры. */
    @Column(name = "department_name", nullable = false)
    private String departmentName;

    /** Связь "многие-к-одному" - Факультет. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    /** Связь "один-ко-многим" - Студент. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Student> students;

    /** Связь "один-ко-многим" - Преподаватель. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Teacher> teachers;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}