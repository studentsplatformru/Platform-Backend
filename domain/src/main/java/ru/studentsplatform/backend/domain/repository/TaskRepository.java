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

	@Query("select att from Task att where att.scheduleUserCell.user.id = :userId and att.isDone = :isDone")
	List<Task> findByIsDoneByUserId(@Param("userId") Long userId, @Param("isDone") Boolean isDone);

	@Query("select att from Task att where att.scheduleUserCell.user.id = :userId " +
			"and att.scheduleUserCell.discipline.semester = :semester")
	List<Task> findBySemesterByUserId(@Param("userId") Long userId, @Param("semester") Long semester);

	@Query("select att from Task att where att.scheduleUserCell.user.id = :userId " +
			"and att.scheduleUserCell.discipline.subject.id = :subjectId")
	List<Task> findBySubjectIdByUserId(@Param("userId") Long userId, @Param("subjectId") Long subjectId);

	@Query("select att from Task att where att.scheduleUserCell.user.team.id = :teamId")
	List<Task> findByTeamId(@Param("teamId") Long teamId);
}
