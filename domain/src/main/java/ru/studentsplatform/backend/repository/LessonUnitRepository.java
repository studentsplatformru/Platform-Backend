package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.LessonUnit;

public interface LessonUnitRepository extends JpaRepository<LessonUnit, Long> {
}
