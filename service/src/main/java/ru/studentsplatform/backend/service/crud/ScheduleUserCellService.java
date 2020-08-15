package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.domain.pojo.filters.ScheduleUserCellFilterPOJO;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;

import java.util.List;

public interface ScheduleUserCellService extends AbstractService<ScheduleUserCell> {

	/**
	 * Сохраняет сущность в БД.
	 *
	 * @param newEntity Объект сущности для сохранения
	 * @return Сохраненная сущность
	 */
	@Override
	ScheduleUserCell create(ScheduleUserCell newEntity);

	/**
	 * Производит поиск сущности в БД.
	 *
	 * @param id Id искомой сущности
	 * @return Искомая сущность
	 */
	@Override
	ScheduleUserCell getById(Long id);

	/**
	 * Производит поиск всех сущностей данного типа в БД.
	 *
	 * @return Лист искомых сущностей
	 */
	@Override
	List<ScheduleUserCell> getAll();

	/**
	 * Производит обновление сущности в БД заданными параметрами.
	 *
	 * @param updatedEntity параметры для обновления сущности
	 * @param id            Id сущности, которая должна быть обновлена.
	 * @return обновлённая сущность.
	 */
	@Override
	ScheduleUserCell update(ScheduleUserCell updatedEntity, Long id);

	/**
	 * Производит удаление сущности из БД.
	 *
	 * @param id Id сущности, которая должна быть удалена
	 * @return успешено ли прошло удаление
	 */
	@Override
	boolean delete(Long id);

	/**
	 * Позволяет получить посещения студентов студентов по определенным фльтрам.
	 *
	 * @param filter DTO, содержащий фильтрующие параметры
	 * @return лист обхектов студенческого расписания
	 */
	List<ScheduleUserCell> getFiltered(ScheduleUserCellFilterPOJO filter);

	/**
	 * Возвращает строку, описывающую процентное отношение посещенных занятий по
	 * заданным фильрам.
	 *
	 * @param filter объект, содержащий поля с фильтрами
	 * @return процентное отношение посещенных занятий
	 */
	String getFilteredPercentage(ScheduleUserCellFilterPOJO filter);

}
