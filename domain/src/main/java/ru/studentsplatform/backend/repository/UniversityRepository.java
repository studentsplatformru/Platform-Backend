package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.university.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, String> {
}
