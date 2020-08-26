package ru.studentsplatform.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;
import ru.studentsplatform.backend.entities.model.user.TelegramFollower;

import java.util.List;

/**
 * Репозиторий для работы с БД сущностью {@link TelegramFollower}.
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
@Repository
public interface FollowerRepository extends JpaRepository<TelegramFollower, Long> {
    List<TelegramFollower> findAllByTeam(SpbuTeam team);
}
