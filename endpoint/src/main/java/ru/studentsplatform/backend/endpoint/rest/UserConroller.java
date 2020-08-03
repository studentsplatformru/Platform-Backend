package ru.studentsplatform.backend.endpoint.rest;


import org.springframework.http.ResponseEntity;
import ru.studentsplatform.backend.domain.dto.user.UserDTO;

import java.util.List;

/**
 * Контроллер user , позволяющий создать,удалить,получить,обновить user (CRUD контроллер).
 */
public interface UserConroller extends AbstractCRUDController<UserDTO> {
	/**
	 * Маппинг контроллера.
	 */
	String BASE_URL = AbstractCRUDController.BASE_URL + "/user";

	/**
	 * Создает новую запись в таблице usr по заданным параметрам.
	 *
	 * @param dto объект user с параметрами, полученный с серверной части.
	 * @return созданный объект.
	 */
	@Override
	ResponseEntity<UserDTO> create(UserDTO dto);

	/**
	 * Получает запись из БД по заданному id.
	 *
	 * @param id id записи в БД.
	 * @return найденная запись.
	 */
	@Override
	ResponseEntity<UserDTO> getById(Long id);

	/**
	 * Получает все записи из БД.
	 *
	 * @return лист записей.
	 */
	@Override
	ResponseEntity<List<UserDTO>> getAll();

	/**
	 * Обновляет запись с данным id из БД.
	 *
	 * @param dto новые данные.
	 * @param id  id записи.
	 * @return обновленная запись.
	 */
	@Override
	ResponseEntity<UserDTO> update(UserDTO dto, Long id);

	/**
	 * Удаляет запись c данным id из БД.
	 *
	 * @param id id удаляемой записи.
	 * @return true в случае успешного удаления, false в случае если запись не существует.
	 */
	@Override
	ResponseEntity<Boolean> delete(Long id);
}
