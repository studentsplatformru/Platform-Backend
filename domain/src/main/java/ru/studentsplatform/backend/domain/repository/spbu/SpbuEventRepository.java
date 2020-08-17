package ru.studentsplatform.backend.domain.repository.spbu;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;

import java.util.List;

public interface SpbuEventRepository extends JpaRepository<SpbuEvent, Long> {

	List<SpbuEvent> findByTeamName(String name);

}
