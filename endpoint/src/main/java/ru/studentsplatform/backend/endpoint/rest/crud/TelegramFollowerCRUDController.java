package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import ru.studentsplatform.backend.domain.dto.telegram.TelegramFollowerDTO;
import ru.studentsplatform.backend.system.exception.core.Fault;

import java.util.List;

public interface TelegramFollowerCRUDController extends AbstractCRUDController<TelegramFollowerDTO> {

    String BASE_URL = AbstractCRUDController.BASE_URL + "/telegram/follower";

    /**
     * Создаёт новую запись подписки.
     *
     * @param telegramFollowerDTO параметры для создания записи.
     * @return созданная запись.
     */
    @Override
    ResponseEntity<TelegramFollowerDTO> create(TelegramFollowerDTO telegramFollowerDTO);

    /**
     * Возвращает параметры записи с заданным Id.
     *
     * @param id параметр для поиска.
     * @return искомую запись.
     * @throws Fault в случае системной ошибки.
     */
    @Override
    ResponseEntity<TelegramFollowerDTO> getById(Long id) throws Fault;

    /**
     * Возвращает все записи в таблице.
     *
     * @return все записи в таблице.
     */
    @Override
    ResponseEntity<List<TelegramFollowerDTO>> getAll();

    /**
     * Обновляет запись в таблице.
     *
     * @param telegramFollowerDTO параметры для обновления.
     * @param id параметр поиска записи.
     * @return обновленное поле.
     */
    @Override
    ResponseEntity<TelegramFollowerDTO> update(TelegramFollowerDTO telegramFollowerDTO, Long id);

    /**
     * Удаляет запись из таблицы.
     *
     * @param id параметр для поиска нужной строки.
     * @return результат удаления.
     */
    @Override
    ResponseEntity<Boolean> delete(Long id);
}
