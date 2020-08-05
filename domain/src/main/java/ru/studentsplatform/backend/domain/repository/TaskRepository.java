package ru.studentsplatform.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.university.Task;

import java.time.OffsetDateTime;
import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("select att from Task att left join fetch  att.attachments " +
			"where (:userId is null or att.scheduleUserCell.user.id = :userId) " +
			"and (:subjectId is null or att.scheduleUserCell.discipline.subject.id = :subjectId) " +
			"and (:groupId is null or att.scheduleUserCell.user.team.id = :groupId) " +
			"and (:usrCellId is null or att.scheduleUserCell.id = :usrCellId) " +
			"and (:semester is null or att.scheduleUserCell.discipline.semester = :semester) " +
			"and (coalesce(:startTime, null) is null or att.scheduleUserCell.scheduleCell.startClass >= :startTime) " +
			"and (coalesce(:endTime, null) is null or att.scheduleUserCell.scheduleCell.endClass <= :endTime)")
	List<Task> findFiltered(@Param("userId") Long userId,
							@Param("usrCellId") Long usrCellId,
							@Param("subjectId") Long subjectId,
							@Param("groupId") Long groupId,
							@Param("semester") Integer semester,
							@Param("startTime") OffsetDateTime startTime,
							@Param("endTime") OffsetDateTime endTime);

}
