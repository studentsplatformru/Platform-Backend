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
 * Класс предметов в университете.
 */
@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {

    /** Поле название предмета. */
    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    /** Связь "многие-к-одному" - Группа. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    /** Связь "один-ко-многим" - Конкретная пара (её расписание). */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private List<LessonUnit> lessonUnits;

    /** Связь "один-ко-многим" - Материал. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private List<Material> materials;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<LessonUnit> getLessonUnits() {
        return lessonUnits;
    }

    public void setLessonUnits(List<LessonUnit> lessonUnits) {
        this.lessonUnits = lessonUnits;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}
