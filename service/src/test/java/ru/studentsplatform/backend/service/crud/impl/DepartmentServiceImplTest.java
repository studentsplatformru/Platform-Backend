package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.DepartmentRepository;
import ru.studentsplatform.backend.domain.repository.FacultyRepository;
import ru.studentsplatform.backend.entities.model.university.Department;
import ru.studentsplatform.backend.entities.model.university.Faculty;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

	@Mock
	private DepartmentRepository departmentRepository;

	@Mock
	private FacultyRepository facultyRepository;


	@InjectMocks
	private DepartmentServiceImpl departmentService;

	/**
	 * Проверка того, что метод create возвращает созданную кафедру.
	 * Сымитировано поведение saveAndFlush.
	 */
	@Test
	void createTest() {
		var department = mock(Department.class);
		var faculty = mock(Faculty.class);
		doReturn(faculty).when(department).getFaculty();
		doReturn(1L).when(faculty).getId();
		doReturn(true).when(facultyRepository).existsById(1L);
		doReturn(department).when(departmentRepository).save(department);

		var result = departmentService.create(department);
		assertEquals(department, result);
		doReturn(2L).when(faculty).getId();
		assertThrows(BusinessException.class, () -> departmentService.create(department));
	}

	/**
	 * Проверка того, что метод findById возвращает созданную кафедру
	 * и кидает NoSuchElementException при её отсутствии.
	 */
	@Test
	void getByIdTest() {
		Department department = new Department();

		when(departmentRepository.findById(2L)).thenReturn(java.util.Optional.of(department));

		assertEquals(departmentRepository.findById(2L).orElseThrow(), departmentService.getById(2L));
		assertThrows(BusinessException.class, () -> departmentService.getById(3L));
	}

	/**
	 * Проверка того, что метод getAll выводит результат метода findAll репозитория.
	 */
	@Test
	void getAllTest() {
		assertEquals(departmentService.getAll(), departmentRepository.findAll());
	}

	/**
	 * Проверка того, что метод update возвращает обновлённую кафедру с Id,
	 * заданным в параметре.
	 */
	@Test
	void updateTest() {
		Department newDepartment = new Department();

		when(departmentRepository.saveAndFlush(newDepartment)).thenReturn(newDepartment);
		assertEquals(newDepartment, departmentService.update(newDepartment, 3L));
		assertEquals(3L, departmentService.update(newDepartment, 3L).getId());
	}

	/**
	 * Проверка того, что метод delete возвращает true, если
	 * EmptyResultDataAccessException не был брошен,
	 * и возвращает false в обратном случае.
	 */
	@Test
	void deleteTest() {
		assertTrue(departmentService.delete(anyLong()));

		doThrow(EmptyResultDataAccessException.class).when(departmentRepository).deleteById(anyLong());
		assertFalse(departmentService.delete(anyLong()));
	}

}
