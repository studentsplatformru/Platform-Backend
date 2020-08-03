package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.university.FacultyDTO;
import ru.studentsplatform.backend.entities.model.university.Faculty;
import ru.studentsplatform.backend.entities.model.university.University;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FacultyMapperTest {

	@Mock
	DepartmentMapper departmentMapper;

	@Mock
	DirectionMapper directionMapper;

	@InjectMocks
	FacultyMapper mapper = new FacultyMapperImpl();

	/**
	 * Проверка того, что маппер может создать DTO на основе сущности
	 */
	@Test
	void facultyToFacultyDTOTest() {
		Faculty entity = new Faculty();
		University university = new University();
		university.setId(2L);
		entity.setId(1L);
		entity.setFaculty("test");
		entity.setUniversity(university);
		FacultyDTO dto = mapper.facultyToFacultyDTO(entity);
		assertEquals(dto.getFaculty(), entity.getFaculty());
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getUniversityId(), entity.getUniversity().getId());
	}

	/**
	 * Проверка того, что маппер способен создать сущность на основе DTO
	 */
	@Test
	void facultyDTOtoFacultyTest() {
		FacultyDTO dto = new FacultyDTO();
		dto.setId(1L);
		dto.setFaculty("test");
		dto.setUniversityId(2L);
		Faculty entity = mapper.facultyDTOToFaculty(dto);
		assertEquals(dto.getFaculty(), entity.getFaculty());
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getUniversityId(), entity.getUniversity().getId());
	}

	/**
	 * Проверка того, что маппер способен создать лист DTO на основе листа сущностей
	 */
	@Test
	void listFacultyToFacultyDTOTest() {
		List<Faculty> facultyList = new LinkedList<>();
		Faculty testFaculty = new Faculty();
		testFaculty.setId(3L);
		facultyList.add(testFaculty);
		facultyList.add(testFaculty);
		List<FacultyDTO> facultyDTOS = mapper.listFacultyToFacultyDTO(facultyList);
		assertEquals(3L, facultyDTOS.get(0).getId());
		assertEquals(3L, facultyDTOS.get(1).getId());
	}

	/**
	 * Проверка того, что маппер способен создать лист сущностей на основе листа DTO
	 */
	@Test
	void listFacultyDTOtoFacultyTest() {
		List<FacultyDTO> facultyDTOS = new LinkedList<>();
		FacultyDTO testFacultyDTO = new FacultyDTO();
		testFacultyDTO.setId(5L);
		facultyDTOS.add(testFacultyDTO);
		facultyDTOS.add(testFacultyDTO);
		List<Faculty> facultys = mapper.listFacultyDTOToFaculty(facultyDTOS);
		assertEquals(5L, facultys.get(0).getId());
		assertEquals(5L, facultys.get(1).getId());
	}
}
