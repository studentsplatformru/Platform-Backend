package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Direction;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
