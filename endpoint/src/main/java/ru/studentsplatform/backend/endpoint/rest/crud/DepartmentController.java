package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.DepartmentDTO;

import java.util.List;

public interface DepartmentController extends AbstractController<DepartmentDTO> {

    String BASE_URL = AbstractController.BASE_URL + "/department";

    /**
     * Создаёт новую запись в таблице department по заданным параметрам.
     * @param departmentDTO Параметры для записи в таблицу
     * @return параметры созданной записи
     */
    @Override
    ResponseEntity<DepartmentDTO> create(@RequestBody DepartmentDTO departmentDTO);

    /**
     * Возвращает параметры записи с заданным Id.
     * @param id Id искомой записи
     * @return параметры искомой записи
     */
    @Override
    ResponseEntity<DepartmentDTO> getById(@PathVariable Long id);

    /**
     * Возвращает параметры всех записей в таблице department.
     * @return лист параметров записей в таблице
     */
    @Override
    ResponseEntity<List<DepartmentDTO>> getAll();

    /**
     * Обновляет данные записи в таблице department.
     * @param departmentDTO данные для обновления
     * @param id Id записи, которая будет обновлена
     * @return параметры обновленной записи
     */
    @Override
    ResponseEntity<DepartmentDTO> update(@RequestBody DepartmentDTO departmentDTO, @PathVariable Long id);

    @Override
    ResponseEntity<Boolean> delete(@PathVariable Long id);
}
