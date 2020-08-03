package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.domain.dto.university.SubjectDTO;
import ru.studentsplatform.backend.entities.model.university.Subject;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubjectMapperTest {

	SubjectMapper mapper = new SubjectMapperImpl();

	/**
	 * Проверка того, что маппер может создать DTO на основе сущности
	 */
	@Test
	void subjectToSubjectDTOTest() {
		Subject entity = new Subject();
		entity.setSubjectName("test");
		SubjectDTO dto = mapper.subjectToSubjectDTO(entity);
		assertEquals(dto.getSubjectName(), entity.getSubjectName());
	}

	/**
	 * Проверка того, что маппер может создать сущность на основе DTO
	 */
	@Test
	void subjectDTOToSubjectTest() {
		SubjectDTO dto = new SubjectDTO();
		dto.setSubjectName("test");
		Subject entity = mapper.subjectDTOToSubject(dto);
		assertEquals(dto.getSubjectName(), entity.getSubjectName());
	}

	/**
	 * Проверка того, что маппер способен создать лист DTO на основе листа сущностей
	 */
	@Test
	void listSubjectToSubjectDTOTest() {
		List<Subject> subjectList = new LinkedList<>();
		Subject testSubject = new Subject();
		testSubject.setId(3L);
		subjectList.add(testSubject);
		subjectList.add(testSubject);
		List<SubjectDTO> subjectDTOS = mapper.listSubjectToSubjectDTO(subjectList);
		assertEquals(3L, subjectDTOS.get(0).getId());
		assertEquals(3L, subjectDTOS.get(1).getId());
	}

	/**
	 * Проверка того, что маппер способен создать лист сущностей на основе листа DTO
	 */
	@Test
	void listSubjectDTOtoSubjectTest() {
		List<SubjectDTO> subjectDTOS = new LinkedList<>();
		SubjectDTO testSubjectDTO = new SubjectDTO();
		testSubjectDTO.setId(5L);
		subjectDTOS.add(testSubjectDTO);
		subjectDTOS.add(testSubjectDTO);
		List<Subject> subjects = mapper.listSubjectDTOToSubject(subjectDTOS);
		assertEquals(5L, subjects.get(0).getId());
		assertEquals(5L, subjects.get(1).getId());
	}

}
