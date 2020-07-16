package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
