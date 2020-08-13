package ru.studentsplatform.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.university.Task;

/**
 * JPA репозиторий сущности Task, с применением технологии queryJpl.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>,
		QuerydslPredicateExecutor<Task> {
}
