package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.BaseEntity;

import java.util.List;

/**
 * Заготовка для создания интерфейсов сервисов.
 *
 * @param <Entity> Класс сущности, для которой создается сервис.
 */
public interface AbstractService<Entity extends BaseEntity> {

	Entity create(Entity newEntity);

	Entity getById(Long id);

	List<Entity> getAll();

	Entity update(Entity updatedEntity, Long id);

	boolean delete(Long id);
}