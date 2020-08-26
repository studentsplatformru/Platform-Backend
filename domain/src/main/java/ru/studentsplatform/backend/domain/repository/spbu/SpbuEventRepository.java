package ru.studentsplatform.backend.domain.repository.spbu;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;

import java.time.LocalDate;
import java.util.List;

public interface SpbuEventRepository extends JpaRepository<SpbuEvent, Long> {

	List<SpbuEvent> findByTeamName(String name);

	List<SpbuEvent> findByDateAndTeam(LocalDate date, SpbuTeam team);

}
