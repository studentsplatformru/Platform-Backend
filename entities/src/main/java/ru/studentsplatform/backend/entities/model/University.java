package ru.studentsplatform.backend.entities.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

public class University extends BaseEntity {
    @Column(name = "university_name")
    private String universityName;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private Set<Faculty> faculties;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private Set<Library> libraries;

    public Set<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(Set<Library> libraries) {
        this.libraries = libraries;
    }
    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
    }


}
