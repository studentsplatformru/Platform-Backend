package ru.studentsplatform.backend.entities.model;

import javax.persistence.*;

@Entity
@Table(name = "material")
public class Material {
    @Id
    @Column(name = "material_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;

    @Column(name = "link",nullable = false)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "library_id")
    private Library library;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        materialId = materialId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
