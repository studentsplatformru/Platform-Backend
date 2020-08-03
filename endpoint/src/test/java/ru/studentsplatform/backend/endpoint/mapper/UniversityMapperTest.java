package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.university.UniversityDTO;
import ru.studentsplatform.backend.entities.model.enums.UniversityEnum;
import ru.studentsplatform.backend.entities.model.university.University;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UniversityMapperTest {

	@Mock
	FacultyMapper facultyMapper = new FacultyMapperImpl();

	@InjectMocks
	UniversityMapper mapper = new UniversityMapperImpl();

	/**
	 * Проверка того, что маппер может создать сущность на основе DTO
	 */
	@Test
	void universityToUniversityDTOTest() {
		University entity = new University();
		entity.setUniversity(UniversityEnum.SPBU);
		UniversityDTO dto = mapper.universityToUniversityDTO(entity);
		assertEquals(dto.getUniversity(), entity.getUniversity().toString());
	}

	/**
	 * Проверка того, что маппер может DTO сущность на основе сущности
	 */
	@Test
	void universityDTOToUniversity() {
		UniversityDTO dto = new UniversityDTO();
		dto.setUniversity("ITMO");
		University entity = mapper.universityDTOToUniversity(dto);
		assertEquals(dto.getUniversity(), entity.getUniversity().toString());
	}

	/**
	 * Проверка того, что маппер способен создать лист DTO на основе листа сущностей
	 */
	@Test
	void listUniversityToUniversityDTOTest() {
		List<University> universityList = new LinkedList<>();
		University testUniversity = new University();
		testUniversity.setId(3L);
		universityList.add(testUniversity);
		universityList.add(testUniversity);
		List<UniversityDTO> universityDTOS = mapper.listUniversityToUniversityDTO(universityList);
		assertEquals(3L, universityDTOS.get(0).getId());
		assertEquals(3L, universityDTOS.get(1).getId());
	}

	/**
	 * Проверка того, что маппер способен создать лист сущностей на основе листа DTO
	 */
	@Test
	void listUniversityDTOtoUniversityTest() {
		List<UniversityDTO> universityDTOS = new LinkedList<>();
		UniversityDTO testUniversityDTO = new UniversityDTO();
		testUniversityDTO.setId(5L);
		universityDTOS.add(testUniversityDTO);
		universityDTOS.add(testUniversityDTO);
		List<University> universitys = mapper.listUniversityDTOToUniversity(universityDTOS);
		assertEquals(5L, universitys.get(0).getId());
		assertEquals(5L, universitys.get(1).getId());
	}

}
