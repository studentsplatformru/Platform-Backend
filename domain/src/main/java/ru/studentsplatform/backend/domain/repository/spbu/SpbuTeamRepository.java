package ru.studentsplatform.backend.domain.repository.spbu;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;

public interface SpbuTeamRepository extends JpaRepository<SpbuTeam, Long> {

	SpbuTeam findByName(String name);

}
