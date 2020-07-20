package ru.studentsplatform.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.LessonUnit;

@Repository
public interface LessonUnitRepository extends JpaRepository<LessonUnit, Long> {
}
