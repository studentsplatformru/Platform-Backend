package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.university.PlaceStudy;

import java.util.List;

public interface PlaceStudyService extends AbstractService<PlaceStudy> {

	/**
	 * Сохраняет сущность в БД.
	 *
	 * @param newPlaceStudy Объект сущности для сохранения
	 * @return Сохраненная сущность
	 */
	@Override
	PlaceStudy create(PlaceStudy newPlaceStudy);

	/**
	 * Производит поиск сущности в БД.
	 *
	 * @param id Id искомой сущности
	 * @return Искомая сущность
	 */
	@Override
	PlaceStudy getById(Long id);

	/**
	 * Производит поиск всех сущностей данного типа в БД.
	 *
	 * @return Лист искомых сущностей
	 */
	@Override
	List<PlaceStudy> getAll();

	/**
	 * Производит обновление сущности в БД заданными параметрами.
	 *
	 * @param updatedPlaceStudy параметры для обновления сущности
	 * @param id                Id сущности, которая должна быть обновлена.
	 * @return обновлённая сущность.
	 */
	@Override
	PlaceStudy update(PlaceStudy updatedPlaceStudy, Long id);

	/**
	 * Производит удаление сущности из БД.
	 *
	 * @param id Id сущности, которая должна быть удалена
	 * @return успешено ли прошло удаление
	 */
	@Override
	boolean delete(Long id);
}
