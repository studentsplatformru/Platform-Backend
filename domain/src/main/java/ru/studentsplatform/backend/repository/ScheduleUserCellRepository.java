package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;

@Repository
public interface ScheduleUserCellRepository extends JpaRepository<ScheduleUserCell, Long> {
}
