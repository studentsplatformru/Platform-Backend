package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.university.DepartmentDTO;
import ru.studentsplatform.backend.entities.model.university.Department;
import ru.studentsplatform.backend.entities.model.university.Faculty;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DepartmentMapperTest {

	@InjectMocks
	private DepartmentMapper mapper = new DepartmentMapperImpl();

	/**
	 * Проверка того, что маппер может DTO сущность на основе сущности
	 */
	@Test
	void departmentToDepartmentDTOTest() {
		Department department = new Department();
		Faculty faculty = new Faculty();
		faculty.setId(2L);
		department.setId(1L);
		department.setDepartment("test");
		department.setFaculty(faculty);

		DepartmentDTO dto = mapper.departmentToDepartmentDTO(department);
		assertEquals(dto.getId(), department.getId());
		assertEquals(dto.getDepartment(), department.getDepartment());
		assertEquals(dto.getFacultyId(), department.getFaculty().getId());

	}

	/**
	 * Проверка того, что маппер может создать сущность на основе DTO
	 */
	@Test
	void departmentDTOToDepartmentTest() {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(2L);
		dto.setFacultyId(1L);
		dto.setDepartment("test");
		Department department = mapper.departmentDTOToDepartment(dto);
		assertEquals(department.getId(), dto.getId());
		assertEquals(department.getDepartment(), dto.getDepartment());
		assertEquals(department.getFaculty().getId(), dto.getFacultyId());
	}

	/**
	 * Проверка того, что маппер может создать лист DTO на основе листа сущностей
	 */
	@Test
	void listDepartmentToDepartmentDTOTest() {
		List<Department> departmentList = new LinkedList<>();
		Department testDepartment = new Department();
		testDepartment.setId(3L);
		departmentList.add(testDepartment);
		departmentList.add(testDepartment);
		List<DepartmentDTO> departmentDTOS = mapper.listDepartmentToDepartmentDTO(departmentList);
		assertEquals(3L, departmentDTOS.get(0).getId());
		assertEquals(3L, departmentDTOS.get(1).getId());
	}

	/**
	 * Проверка того, что маппер может создать лист сущностей на основе листа DTO
	 */
	@Test
	void listDepartmentDTOtoDepartmentTest() {
		List<DepartmentDTO> departmentDTOS = new LinkedList<>();
		DepartmentDTO testDepartmentDTO = new DepartmentDTO();
		testDepartmentDTO.setId(5L);
		departmentDTOS.add(testDepartmentDTO);
		departmentDTOS.add(testDepartmentDTO);
		List<Department> departments = mapper.listDepartmentDTOToDepartment(departmentDTOS);
		assertEquals(5L, departments.get(0).getId());
		assertEquals(5L, departments.get(1).getId());
	}

}
