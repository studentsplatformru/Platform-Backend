package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.schedule.ScheduleCell;

import java.util.List;

public interface ScheduleCellService extends AbstractService<ScheduleCell> {

    /**
     * Сохраняет сущность в БД.
     * @param newEntity Объект сущности для сохранения
     * @return Сохраненная сущность
     */
    @Override
    ScheduleCell create(ScheduleCell newEntity);

    /**
     * Производит поиск сущности в БД.
     * @param id Id искомой сущности
     * @return Искомая сущность
     */
    @Override
    ScheduleCell getById(Long id);

    /**
     * Производит поиск всех сущностей данного типа в БД.
     * @return Лист искомых сущностей
     */
    @Override
    List<ScheduleCell> getAll();

    /**
     * Производит обновление сущности в БД заданными параметрами.
     * @param updatedEntity параметры для обновления сущности
     * @param id Id сущности, которая должна быть обновлена.
     * @return обновлённая сущность.
     */
    @Override
    ScheduleCell update(ScheduleCell updatedEntity, Long id);

    /**
     * Производит удаление сущности из БД.
     * @param id Id сущности, которая должна быть удалена
     * @return успешено ли прошло удаление
     */
    @Override
    boolean delete(Long id);
}
