package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.university.Subject;

import java.util.List;

public interface SubjectService extends AbstractService<Subject> {

	/**
	 * Сохраняет сущность в БД.
	 *
	 * @param newEntity Объект сущности для сохранения
	 * @return Сохраненная сущность
	 */
	@Override
	Subject create(Subject newEntity);

	/**
	 * Производит поиск сущности в БД.
	 *
	 * @param id Id искомой сущности
	 * @return Искомая сущность
	 */
	@Override
	Subject getById(Long id);

	/**
	 * Производит поиск всех сущностей данного типа в БД.
	 *
	 * @return Лист искомых сущностей
	 */
	@Override
	List<Subject> getAll();

	/**
	 * Производит обновление сущности в БД заданными параметрами.
	 *
	 * @param updatedEntity параметры для обновления сущности
	 * @param id            Id сущности, которая должна быть обновлена.
	 * @return обновлённая сущность.
	 */
	@Override
	Subject update(Subject updatedEntity, Long id);

	/**
	 * Производит удаление сущности из БД.
	 *
	 * @param id Id сущности, которая должна быть удалена
	 * @return успешено ли прошло удаление
	 */
	@Override
	boolean delete(Long id);
}
