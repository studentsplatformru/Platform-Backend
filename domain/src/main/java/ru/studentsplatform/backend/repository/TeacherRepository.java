package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}