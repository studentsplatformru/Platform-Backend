package ru.studentsplatform.backend.entities.model;

import javax.persistence.*;
import java.util.Set;

public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_name")
    private String universityName;

    @OneToMany(mappedBy = "university",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Faculty> faculties;
    /*
    TODO библиотека
    @OneToMany(mappedBy = "university",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Library> libraries;

    public Set<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(Set<Library> libraries) {
        this.libraries = libraries;
    }
     */
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
