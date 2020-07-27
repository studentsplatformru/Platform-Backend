package ru.studentsplatform.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;

import java.util.List;

public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {
	@Query("select att from TaskAttachment att where att.task.id = :id")
	List<TaskAttachment> findByTaskId(@Param("id") Long taskId);
}
