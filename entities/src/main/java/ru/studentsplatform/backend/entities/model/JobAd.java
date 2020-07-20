package ru.studentsplatform.backend.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Класс объявлений о работе.
 */
@Entity
@Table(name = "job_ad")
public class JobAd extends BaseEntity {

    /** Поле категория. */
    @Column(name = "category", nullable = false)
    private String category;

    /** Поле название работы. */
    @Column(name = "job_name", nullable = false)
    private String jobName;

    /** Поле описание работы. */
    @Column(name = "description", nullable = false)
    private String description;

    /** Поле ссылка. */
    @Column(name = "link", nullable = false)
    private String link;

    /** Связь "многие-к-одному" - Факультет. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

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
