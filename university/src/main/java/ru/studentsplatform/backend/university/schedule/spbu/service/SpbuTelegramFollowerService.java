package ru.studentsplatform.backend.university.schedule.spbu.service;

import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;
import ru.studentsplatform.backend.entities.model.user.TelegramFollower;

import java.util.List;

/**
 * CRUD для {@link TelegramFollower}
 *
 * @author Danila K (karnacevich5323537@gmail.com) (22.08.2020).
 */
public interface SpbuTelegramFollowerService {

    /**
     * @param id уникальный идентификатор пользователя в Telegram.
     * @param team группа пользователя.
     * @return подписчика.
     */
    TelegramFollower create(Long id, SpbuTeam team);

    /**
     * @param id параметр для поиска.
     * @return искомая запись.
     */
    TelegramFollower getById(Long id);
    /**
     * @param id уникальный идентификатор пользователя в Telegram.
     */
    void delete(Long id);

    /**
     * @return список всех подписчиков.
     */
    List<TelegramFollower> getAll();

    /**
     * @param team группа пользователя.
     * @return список подписчиков с данной группой.
     */
    List<TelegramFollower> getByTeam(SpbuTeam team);
}
