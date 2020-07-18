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
@Table(name = "subject")
public class Subject extends BaseEntity {

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private Set<LessonUnit> lessonUnits;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private Set<Material> materials;

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

    public Set<LessonUnit> getLessonUnits() {
        return lessonUnits;
    }

    public void setLessonUnits(Set<LessonUnit> lessonUnits) {
        this.lessonUnits = lessonUnits;
    }

    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }
}
