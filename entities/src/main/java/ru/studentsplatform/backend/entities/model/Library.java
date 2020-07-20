package ru.studentsplatform.backend.entities.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Класс библиотеки.
 */
@Entity
@Table(name = "library")
public class Library extends BaseEntity {

    /** Связь "многие-к-одному" - Университет. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_name")
    private University university;

    /** Связь "один-ко-многим" - Материал. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "library")
    private List<Material> materials;

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}
