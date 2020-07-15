package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.University;

public interface UniversityRepository extends JpaRepository<University, String> {
}
