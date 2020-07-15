package ru.studentsplatform.backend.entities.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @Column(name = "faculty_name")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "university_name")
    private University university;

    //TODO кафедры

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "faculty")
    private Set<StudentCouncil> studentCouncils;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "faculty")
    private Set<JobAd> jobAds;

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

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
