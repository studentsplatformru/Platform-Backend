package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
