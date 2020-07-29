package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.user.UserInfoDTO;

/**
 * Контроллер user info, позволяющий созать,удалить,получить,обновить user info.
 * А также позволяющий загрузить изображение и получить изображение,
 * связанное с соответствующей записью user info в БД.
 */
public interface UserInfoController extends AbstractController<UserInfoDTO> {
    /**
     * Путь к user info.
     */
    String BASE_URL = AbstractController.BASE_URL + "/userinfo";

    /**
     * Создает новую запись в таблице user_info по заданным параметрам(без изображения).
     * @param newInstanceRequest объект user info с параметрами, полученный с серверной части.
     * @return созданный объект.
     */
    @Override
    ResponseEntity<UserInfoDTO> create(UserInfoDTO newInstanceRequest);

    /**
     * Получает запись из БД по заданному id.
     * @param id id записи в БД user_info.
     * @return найденная запись.
     */
    @Override
    ResponseEntity<UserInfoDTO> getById(Long id);

    /**
     * Загружает изображение в запись с заданным id.
     *
     * @param userInfoId id записи в таблице user_info
     * @param file       загружаемое изображение
     * @return true если загрузка удалась.
     */
    @PostMapping("/uploadimage")
    ResponseEntity<Boolean> uploadImage(@RequestParam("id") Long userInfoId, @RequestParam("file") MultipartFile file);

    /**
     * Получает изображение по заданному id из БД user_info.
     *
     * @param userInfoId id записи из user_info
     * @return Возвращает найденное изображение.
     */
    @GetMapping("/getimage")
    ResponseEntity<ByteArrayResource> getImage(@RequestParam("id") Long userInfoId);
}
