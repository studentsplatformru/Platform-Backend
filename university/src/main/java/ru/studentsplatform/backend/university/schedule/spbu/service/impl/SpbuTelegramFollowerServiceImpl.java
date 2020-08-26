package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.repository.FollowerRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;
import ru.studentsplatform.backend.entities.model.user.TelegramFollower;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuTelegramFollowerService;

import java.util.List;
import java.util.Optional;

/**
 * Реализация {@link SpbuTelegramFollowerService}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (22.08.2020).
 */
@Service
public class SpbuTelegramFollowerServiceImpl implements SpbuTelegramFollowerService {

    private final FollowerRepository followerRepository;

    /**
     * @param followerRepository репозиторий для работы с подписчиками.
     */
    public SpbuTelegramFollowerServiceImpl(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TelegramFollower create(Long id, SpbuTeam team) {

        TelegramFollower follower = new TelegramFollower();
        follower.setTelegramId(id);
        follower.setTeam(team);

        return followerRepository.save(follower);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TelegramFollower getById(Long id) {
        Optional<TelegramFollower> optional = followerRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        followerRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TelegramFollower> getAll() {
        return followerRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TelegramFollower> getByTeam(SpbuTeam team) {
        return followerRepository.findAllByTeam(team);
    }
}
