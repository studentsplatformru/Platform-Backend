package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleUserCellDTO;
import ru.studentsplatform.backend.domain.pojo.filters.ScheduleUserCellFilterPOJO;

import java.util.List;

public interface ScheduleUserCellCRUDController extends AbstractCRUDController<ScheduleUserCellDTO> {
	String BASE_URL = AbstractCRUDController.BASE_URL + "/schedule_user_cell";

	/**
	 * Создаёт новую запись в таблице scheduleUserCell по заданным параметрам.
	 *
	 * @param scheduleUserCellDTO Параметры для записи в таблицу
	 * @return Параметры созданной записи
	 */
	@Override
	ResponseEntity<ScheduleUserCellDTO> create(@RequestBody ScheduleUserCellDTO scheduleUserCellDTO);

	/**
	 * Возвращает параметры записи с заданным Id.
	 *
	 * @param id Id искомой записи
	 * @return Параметры искомой записи
	 */
	@Override
	ResponseEntity<ScheduleUserCellDTO> getById(@PathVariable Long id);

	/**
	 * Возвращает параметры всех записей в таблице scheduleUserCell.
	 *
	 * @return Лист параметров записей в таблице
	 */
	@Override
	ResponseEntity<List<ScheduleUserCellDTO>> getAll();

	/**
	 * Обновляет данные записи в таблице scheduleUserCell.
	 *
	 * @param scheduleUserCellDTO Данные для обновления
	 * @param id                  Id записи, которая будет обновлена
	 * @return Параметры обновленной записи
	 */
	@Override
	ResponseEntity<ScheduleUserCellDTO> update(@RequestBody ScheduleUserCellDTO scheduleUserCellDTO,
											   @PathVariable Long id);

	@Override
	ResponseEntity<Boolean> delete(@PathVariable Long id);

	/**
	 * Позволяет найти ячейки посещения по заданным фильтрам.
	 *
	 * @param filter DTO, содержащий параметры для фильтрации
	 *               Чтобы вывести все ячейки расписаний, дата начала занятий которых:
	 *               поздее заданной даты, необходимо указать startTime
	 *               Раньше заданной даты - endTime
	 *               Между двумя датами - artTime & endTime вместе.
	 * @return Список объектов с параметрами ячеек посещения.
	 */
	@GetMapping("/filter")
	ResponseEntity<List<ScheduleUserCellDTO>> getFiltered(@RequestBody ScheduleUserCellFilterPOJO filter);

	/**
	 * Позволяет найти процентное соотношение посещенных пар в рамках заданных фильтров.
	 *
	 * @param filter Параметры, в рамках которых будет происходить поиск.
	 * @return Процент выполненных задач
	 */
	@GetMapping("/filteredPercentage")
	ResponseEntity<String> getFilteredPresencePercent(@RequestBody ScheduleUserCellFilterPOJO filter);

}
