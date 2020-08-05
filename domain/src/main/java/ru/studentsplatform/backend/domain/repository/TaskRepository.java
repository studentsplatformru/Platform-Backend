package ru.studentsplatform.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.university.QTask;
import ru.studentsplatform.backend.entities.model.university.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>,
		QuerydslPredicateExecutor<Task>, QuerydslBinderCustomizer<QTask> {

	@Override
	default void customize(QuerydslBindings querydslBindings, QTask qTask) {
		querydslBindings.bind(qTask.scheduleUserCell.scheduleCell.startClass).first((path, value) -> path.goe(value));
		querydslBindings.bind(qTask.scheduleUserCell.scheduleCell.endClass).first((path, value) -> path.loe(value));
	}

}
