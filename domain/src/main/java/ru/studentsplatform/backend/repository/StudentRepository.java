package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}