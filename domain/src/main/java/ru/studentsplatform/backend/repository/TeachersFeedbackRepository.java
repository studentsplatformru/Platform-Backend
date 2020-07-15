package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.TeachersFeedback;

public interface TeachersFeedbackRepository extends JpaRepository<TeachersFeedback, Long> {

}