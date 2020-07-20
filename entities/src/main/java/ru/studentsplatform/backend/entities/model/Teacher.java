package ru.studentsplatform.backend.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

/**
 * Класс преподавателей в университете.
 */
@Entity
@Table(name = "teacher")
public class Teacher extends BaseEntity {

    /** Поле id преподавателя. */
    @Id
    @Column(name = "teacher_id")
    private Long teacherId;

    /** Поле персональная страница. */
    @Column(name = "personal_page", nullable = false)
    private String personalPage;

    /** Связь "один-к-одному" - Пользователь. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    /** Связь "один-ко-многим" - Обратная связь с преподавателем. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private Set<TeachersFeedback> teachersFeedback;

    /** Связь "один-ко-многим" - Конкретная пара (её расписание). */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private List<LessonUnit> lessonUnits;

    /** Связь "многие-к-одному" - Направление. */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "direction_id", nullable = true)
    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Set<TeachersFeedback> getTeachersFeedback() {
        return teachersFeedback;
    }

    public void setTeachersFeedback(Set<TeachersFeedback> teachersFeedback) {
        this.teachersFeedback = teachersFeedback;
    }

    public List<LessonUnit> getLessonUnits() {
        return lessonUnits;
    }

    public void setLessonUnits(List<LessonUnit> lessonUnits) {
        this.lessonUnits = lessonUnits;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getPersonalPage() {
        return personalPage;
    }

    public void setPersonalPage(String personalPage) {
        this.personalPage = personalPage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
