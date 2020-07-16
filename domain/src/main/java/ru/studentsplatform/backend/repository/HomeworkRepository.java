package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Homework;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
