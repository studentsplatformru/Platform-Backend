package ru.studentsplatform.backend.service.crud;


import ru.studentsplatform.backend.entities.model.university.Team;

import java.util.List;

public interface TeamService extends AbstractService<Team> {

    /**
     * Сохраняет сущность в БД.
     * @param newEntity Объект сущности для сохранения
     * @return Сохраненная сущность
     */
    @Override
    Team create(Team newEntity);

    /**
     * Производит поиск сущности в БД.
     * @param id Id искомой сущности
     * @return Искомая сущность
     */
    @Override
    Team getById(Long id);

    /**
     * Производит поиск всех сущностей данного типа в БД.
     * @return Лист искомых сущностей
     */
    @Override
    List<Team> getAll();

    /**
     * Производит обновление сущности в БД заданными параметрами.
     * @param updatedEntity параметры для обновления сущности
     * @param id Id сущности, которая должна быть обновлена.
     * @return обновлённая сущность.
     */
    @Override
    Team update(Team updatedEntity, Long id);

    /**
     * Производит удаление сущности из БД.
     * @param id Id сущности, которая должна быть удалена
     * @return успешено ли прошло удаление
     */
    @Override
    boolean delete(Long id);
}
