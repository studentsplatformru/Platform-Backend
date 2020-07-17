package ru.studentsplatform.backend.entities.model;

import javax.persistence.*;

@Entity
@Table(name = "job_ad")
public class JobAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_ad_id")
    private Long jobAdId;

    @Column(name = "category",nullable = false)
    private String category;

    @Column(name = "job_name",nullable = false)
    private String jobName;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "link",nullable = false)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Long getJobAdId() {
        return jobAdId;
    }

    public void setJobAdId(Long jobAdId) {
        this.jobAdId = jobAdId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}