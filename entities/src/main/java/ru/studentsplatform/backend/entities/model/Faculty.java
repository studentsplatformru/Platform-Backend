package ru.studentsplatform.backend.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

/**
 * Класс факультетов, находящихся в университете.
 */
@Entity
@Table(name = "faculty")
public class Faculty extends BaseEntity {

    /** Поле название факультета. */
    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    /** Связь "многие-к-одному" - Университет. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_name")
    private University university;

    /** Связь "один-ко-многим" - Кафедра. */
    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private Set<Department> departments;

    /** Связь "один-ко-многим" - Студенческий совет. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<StudentCouncil> studentCouncils;

    /** Связь "один-ко-многим" - Объявление о работе. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private List<JobAd> jobAds;

    /** Связь "один-ко-многим" - Направление. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<Direction> directions;

    public Set<Direction> getDirections() {
        return directions;
    }

    public void setDirections(Set<Direction> directions) {
        this.directions = directions;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Set<StudentCouncil> getStudentCouncils() {
        return studentCouncils;
    }

    public void setStudentCouncils(Set<StudentCouncil> studentCouncils) {
        this.studentCouncils = studentCouncils;
    }

    public List<JobAd> getJobAds() {
        return jobAds;
    }

    public void setJobAds(List<JobAd> jobAds) {
        this.jobAds = jobAds;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
