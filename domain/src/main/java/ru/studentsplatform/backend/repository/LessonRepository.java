package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
