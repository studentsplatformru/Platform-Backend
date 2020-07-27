package ru.studentsplatform.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleCell;

@Repository
public interface ScheduleCellRepository extends JpaRepository<ScheduleCell, Long> {
}
