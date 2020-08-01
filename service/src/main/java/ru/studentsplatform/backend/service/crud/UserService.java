package ru.studentsplatform.backend.service.crud;


import ru.studentsplatform.backend.entities.model.user.User;

import java.util.List;

/**
 * CRUD сервис, для пользователя.
 */
public interface UserService extends AbstractService<User> {
    /**
     * Создает новую сущность в таблице user.
     * @param newEntity создаваемая сущность.
     * @return созданная сущность.
     */
    @Override
    User create(User newEntity);

    /**
     * Ищет сущность по id.
     * @param id id сущности в таблице user, которую надо получить.
     * @return найденная сущность
     * @throws ru.studentsplatform.backend.system.exception.core.BusinessException
     * - если пользователь не найден (ServiceExceptionReason.USER_NOT_FOUND)
     */
    @Override
    User getById(Long id);

    /**
     * Получает все сущности в таблице user.
     * @return найденные сущности
     */
    @Override
    List<User> getAll();

    /**
     * Обновляет запись с данным id, на новую.
     * @param updatedEntity новые данные.
     * @param id id обновляемой сущности.
     * @return обновленная сущность.
     * @throws ru.studentsplatform.backend.service.exception.core.BusinessException
     * - если пользователь не найден (ServiceExceptionReason.USER_NOT_FOUND)
     */
    @Override
    User update(User updatedEntity, Long id);

    /**
     * Удаляет сущность user с заданным id.
     * @param id id удаляемой сущности.
     * @return в случае успешного удаления true, иначе false.
     */
    @Override
    boolean delete(Long id);
}
