package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.DisciplineDTO;

import java.util.List;

public interface DisciplineController extends AbstractController<DisciplineDTO> {

    String BASE_URL = AbstractController.BASE_URL + "/discipline";

    /**
     * Создаёт новую запись в таблице discipline по заданным параметрам.
     * @param disciplineDTO Параметры для записи в таблицу
     * @return параметры созданной записи
     */
    @Override
    ResponseEntity<DisciplineDTO> create(@RequestBody DisciplineDTO disciplineDTO);

    /**
     * Возвращает параметры записи с заданным Id.
     * @param id Id искомой записи
     * @return параметры искомой записи
     */
    @Override
    ResponseEntity<DisciplineDTO> getById(@PathVariable Long id);

    /**
     * Возвращает параметры всех записей в таблице discipline.
     * @return лист параметров записей в таблице
     */
    @Override
    ResponseEntity<List<DisciplineDTO>> getAll();

    /**
     * Обновляет данные записи в таблице discipline.
     * @param disciplineDTO данные для обновления
     * @param id Id записи, которая будет обновлена
     * @return параметры обновленной записи
     */
    @Override
    ResponseEntity<DisciplineDTO> update(@RequestBody DisciplineDTO disciplineDTO, Long id);

    @Override
    ResponseEntity<Boolean> delete(@PathVariable Long id);
}
