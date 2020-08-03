package ru.studentsplatform.backend.service.crud;

import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.entities.model.user.UserInfo;

import java.util.List;

/**
 * CRUD сервис для информации, прикрепленной к user'у.
 */
public interface UserInfoService extends AbstractService<UserInfo> {
	/**
	 * Создает новую запись в таблице user_info.
	 *
	 * @param newEntity сущность userinfo, которую мы хотим добавить в БД.
	 * @return сохраненная сущность.
	 */
	@Override
	UserInfo create(UserInfo newEntity);

	/**
	 * Получает сущность user info, по данному userInfoId.
	 *
	 * @param id id у user info.
	 * @return Сущность UserInfo
	 */
	@Override
	UserInfo getById(Long id);

	/**
	 * Получает List всех записей сохраненных в таблице user_info.
	 *
	 * @return List записей userInfo.
	 */
	@Override
	List<UserInfo> getAll();

	/**
	 * Заменяет user info с заданным id, на user info с обновленными данными.
	 *
	 * @param updatedEntity сущность user info с новыми данными.
	 * @param id            id обновляемой сущности.
	 * @return Обновленная сущность
	 */
	@Override
	UserInfo update(UserInfo updatedEntity, Long id);

	/**
	 * Удаляет сущность user info с заданным id.
	 *
	 * @param id id удаляемой сущности.
	 * @return в случае успешного удаления true, иначе false.
	 */
	@Override
	boolean delete(Long id);

	/**
	 * Загружает изображение, имя изображения и тип изображения в user info с заданным id.
	 *
	 * @param file       загружаемое изображение.
	 * @param userInfoId id user info, в которую загружается изображение.
	 * @return Успешно/Неуспешно
	 */
	boolean uploadImage(MultipartFile file, Long userInfoId);
}
