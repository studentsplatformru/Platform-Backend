package ru.studentsplatform.backend.entities.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Класс студенческого совета в университете.
 */
@Entity
@Table(name = "student_council")
public class StudentCouncil {

    /** Поле телефон. */
    @Column(name = "phone", nullable = false)
    private String phone;

    /** Поле электронная почта. */
    @Column(name = "email", nullable = false)
    private String email;

    /** Поле аудитория. */
    @Column(name = "audience", nullable = false)
    private String audience;

    /** Поле группа вк. */
    @Column(name = "vk_group", nullable = false)
    private String vkGroup;

    /** Связь "многие-к-одному" - Факультет. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getVkGroup() {
        return vkGroup;
    }

    public void setVkGroup(String vkGroup) {
        this.vkGroup = vkGroup;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
