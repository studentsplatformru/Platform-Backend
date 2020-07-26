package ru.studentsplatform.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.university.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
