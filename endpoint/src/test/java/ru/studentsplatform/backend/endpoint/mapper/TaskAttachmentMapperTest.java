package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.domain.dto.utility.TaskAttachmentDTO;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест методов mapper и способности его переносить поля и объекты из сущности в DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
class TaskAttachmentMapperTest {

	private final TaskAttachmentMapperImpl mapper = new TaskAttachmentMapperImpl();

	/**
	 * Проверка того, что поля сущности и DTO, созданного при помощи mapper, совпадают.
	 */
	@Test
	void taskAttachmentToTaskAttachmentDTOTest() {

		Task task = new Task();
		task.setId(4L);

		TaskAttachment attachment = new TaskAttachment();
		attachment.setFileName("myFile");
		attachment.setTask(task);

		byte[] bytes = {1, 2, 3, 4};
		attachment.setContent(bytes);
		TaskAttachmentDTO dto = mapper.taskAttachmentToTaskAttachmentDTO(attachment);
		assertArrayEquals(dto.getContent(), attachment.getContent());
		assertEquals(dto.getFileName(), attachment.getFileName());
		assertEquals(dto.getTaskId(), attachment.getTask().getId());
		assertEquals(dto.getId(), attachment.getId());
	}

	/**
	 * Проверка того, что поля DTO и сущности, созданной при помощи mapper, совпадают.
	 */
	@Test
	void taskAttachmentDTOToTaskAttachmentTest() {
		Task task = new Task();
		task.setId(4L);

		TaskAttachmentDTO dto = new TaskAttachmentDTO();
		dto.setFileName("myFile");
		dto.setTaskId(task.getId());

		byte[] bytes = {1, 2, 3, 4};
		dto.setContent(bytes);

		TaskAttachment attachment = mapper.taskAttachmentDTOToTaskAttachment(dto);
		assertArrayEquals(dto.getContent(), attachment.getContent());
		assertEquals(dto.getFileName(), attachment.getFileName());
		assertEquals(dto.getTaskId(), attachment.getTask().getId());
		assertEquals(dto.getId(), attachment.getId());
	}

	/**
	 * Проверка того, что mapper корректо прозводит конвертацию листа сущностей в лист DTO,
	 * и корректно переносит их поля.
	 */
	@Test
	void listTaskAttachmentToTaskAttachmentDTOTest() {
		TaskAttachment attachment = new TaskAttachment();
		attachment.setId(5L);

		LinkedList<TaskAttachment> list = new LinkedList<>();
		list.add(attachment);
		list.add(attachment);

		List<TaskAttachmentDTO> dtoList = mapper.listTaskAttachmentToTaskAttachmentDTO(list);
		assertEquals(dtoList.get(0).getId(), list.get(0).getId());
		assertEquals(dtoList.get(1).getId(), list.get(1).getId());
	}

	/**
	 * Проверка того, что mapper корректо прозводит конвертацию листа DTO в лист сущностей,
	 * и корректно переносит их поля.
	 */
	@Test
	void listTaskAttachmentDTOToTaskAttachmentTest() {
		TaskAttachmentDTO dto = new TaskAttachmentDTO();
		dto.setId(3L);

		LinkedList<TaskAttachmentDTO> listDto = new LinkedList<>();
		listDto.add(dto);
		listDto.add(dto);

		List<TaskAttachment> list = mapper.listTaskAttachmentDTOToTaskAttachment(listDto);
		assertEquals(list.get(0).getId(), listDto.get(0).getId());
		assertEquals(list.get(1).getId(), listDto.get(1).getId());
	}
}