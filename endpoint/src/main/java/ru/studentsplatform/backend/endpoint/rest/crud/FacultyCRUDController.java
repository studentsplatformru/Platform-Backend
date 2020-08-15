package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.FacultyDTO;
import ru.studentsplatform.backend.system.exception.core.Fault;

import java.util.List;

public interface FacultyCRUDController extends AbstractCRUDController<FacultyDTO> {

	String BASE_URL = AbstractCRUDController.BASE_URL + "/faculty";

	/**
	 * Создаёт новую запись в таблице faculty по заданным параметрам.
	 *
	 * @param facultyDTO Параметры для записи в таблицу
	 * @return параметры созданной записи
	 */
	@Override
	ResponseEntity<FacultyDTO> create(@RequestBody FacultyDTO facultyDTO);

	/**
	 * Возвращает параметры записи с заданным Id.
	 *
	 * @param id Id искомой записи
	 * @return параметры искомой записи
	 */
	@Override
	ResponseEntity<FacultyDTO> getById(@PathVariable Long id) throws Fault;

	/**
	 * Возвращает параметры всех записей в таблице faculty.
	 *
	 * @return лист параметров записей в таблице
	 */
	@Override
	ResponseEntity<List<FacultyDTO>> getAll();

	/**
	 * Обновляет данные записи в таблице faculty.
	 *
	 * @param facultyDTO данные для обновления
	 * @param id         Id записи, которая будет обновлена
	 * @return параметры обновленной записи
	 */
	@Override
	ResponseEntity<FacultyDTO> update(@RequestBody FacultyDTO facultyDTO, @PathVariable Long id);

	@Override
	ResponseEntity<Boolean> delete(Long id);
}
