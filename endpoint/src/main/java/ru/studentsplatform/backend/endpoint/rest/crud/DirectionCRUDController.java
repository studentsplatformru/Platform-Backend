package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.DirectionDTO;

import java.util.List;

public interface DirectionCRUDController extends AbstractCRUDController<DirectionDTO> {

	String BASE_URL = AbstractCRUDController.BASE_URL + "/direction";

	/**
	 * Создаёт новую запись в таблице direction по заданным параметрам.
	 *
	 * @param directionDTO Параметры для записи в таблицу
	 * @return параметры созданной записи
	 */
	@Override
	ResponseEntity<DirectionDTO> create(@RequestBody DirectionDTO directionDTO);

	/**
	 * Возвращает параметры записи с заданным Id.
	 *
	 * @param id Id искомой записи
	 * @return параметры искомой записи
	 */
	@Override
	ResponseEntity<DirectionDTO> getById(@PathVariable Long id);

	/**
	 * Возвращает параметры всех записей в таблице direction.
	 *
	 * @return лист параметров записей в таблице
	 */
	@Override
	ResponseEntity<List<DirectionDTO>> getAll();

	/**
	 * Обновляет данные записи в таблице direction.
	 *
	 * @param directionDTO данные для обновления
	 * @param id           Id записи, которая будет обновлена
	 * @return параметры обновленной записи
	 */
	@Override
	ResponseEntity<DirectionDTO> update(@RequestBody DirectionDTO directionDTO, @PathVariable Long id);

	@Override
	ResponseEntity<Boolean> delete(@PathVariable Long id);
}
