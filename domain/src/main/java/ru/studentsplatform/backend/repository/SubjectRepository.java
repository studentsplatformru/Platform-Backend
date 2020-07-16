package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
