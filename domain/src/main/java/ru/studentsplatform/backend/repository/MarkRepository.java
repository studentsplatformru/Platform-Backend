package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Mark;

public interface MarkRepository extends JpaRepository<Mark, Long> {
}
