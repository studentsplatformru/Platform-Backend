package ru.studentsplatform.backend.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty extends BaseEntity {

    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_name")
    private University university;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private Set<Department> departments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<StudentCouncil> studentCouncils;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<JobAd> jobAds;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "direction")
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

    public Set<JobAd> getJobAds() {
        return jobAds;
    }

    public void setJobAds(Set<JobAd> jobAds) {
        this.jobAds = jobAds;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
