package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}