package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.university.Department;

import java.util.List;

public interface DepartmentService extends AbstractService<Department> {

    /**
     * Сохраняет сущность в БД.
     * @param newEntity Объект сущности для сохранения
     * @return Сохраненная сущность
     */
    @Override
    Department create(Department newEntity);

    /**
     * Производит поиск сущности в БД.
     * @param id Id искомой сущности
     * @return Искомая сущность
     */
    @Override
    Department getById(Long id);

    /**
     * Производит поиск всех сущностей данного типа в БД.
     * @return Лист искомых сущностей
     */
    @Override
    List<Department> getAll();

    /**
     * Производит обновление сущности в БД заданными параметрами.
     * @param updatedEntity параметры для обновления сущности
     * @param id Id сущности, которая должна быть обновлена.
     * @return обновлённая сущность.
     */
    @Override
    Department update(Department updatedEntity, Long id);

    /**
     * Производит удаление сущности из БД.
     * @param id Id сущности, которая должна быть удалена
     * @return успешено ли прошло удаление
     */
    @Override
    boolean delete(Long id);
}
