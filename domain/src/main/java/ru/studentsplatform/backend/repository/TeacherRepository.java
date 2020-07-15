package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}