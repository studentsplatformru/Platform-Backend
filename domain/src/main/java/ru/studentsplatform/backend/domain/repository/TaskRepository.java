package ru.studentsplatform.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.university.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select att from Task att where att.scheduleUserCell.id = :userCellId")
    List<Task> findByScheduleUserCellId(@Param("userCellId") Long userCellId);

    @Query("select att from Task att where att.scheduleUserCell.user.id = :userId")
    List<Task> findByUserId(@Param("userId") Long userId);
}
