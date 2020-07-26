package ru.studentsplatform.backend.endpoint;

/**
 * Тест методов mapper и способности его переносить поля и объекты из сущности в DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
class TaskAttachmentMapperTest {
//
//	private final TaskAttachmentMapperImpl mapper = new TaskAttachmentMapperImpl();
//
//	/**
//	 * Проверка того, что поля сущности и DTO, созданного при помощи mapper, совпадают.
//	 */
//	@Test
//	void taskAttachmentToTaskAttachmentDTO() {
//
//		Task task = new Task();
//		task.setId(4L);
//
//		TaskAttachment attachment = new TaskAttachment();
//		attachment.setFileName("myFile");
//		attachment.setTask(task);
//
//		byte[] bytes = {1, 2, 3, 4};
//		attachment.setFileContent(bytes);
//		TaskAttachmentDTO dto = mapper.taskAttachmentToTaskAttachmentDTO(attachment);
//		assertArrayEquals(dto.getFileContent(), attachment.getFileContent());
//		assertEquals(dto.getFileName(), attachment.getFileName());
//		assertEquals(dto.getTaskId(), attachment.getTask().getId());
//		assertEquals(dto.getId(), attachment.getId());
//	}
//
//	/**
//	 * Проверка того, что поля DTO и сущности, созданной при помощи mapper, совпадают.
//	 */
//	@Test
//	void taskAttachmentDTOToTaskAttachment() {
//		Task task = new Task();
//		task.setId(4L);
//
//		TaskAttachmentDTO dto = new TaskAttachmentDTO();
//		dto.setFileName("myFile");
//		dto.setTaskId(task.getId());
//
//		byte[] bytes = {1, 2, 3, 4};
//		dto.setFileContent(bytes);
//
//		TaskAttachment attachment = mapper.taskAttachmentDTOToTaskAttachment(dto);
//		assertArrayEquals(dto.getFileContent(), attachment.getFileContent());
//		assertEquals(dto.getFileName(), attachment.getFileName());
//		assertEquals(dto.getTaskId(), attachment.getTask().getId());
//		assertEquals(dto.getId(), attachment.getId());
//	}
//
//	/**
//	 * Проверка того, что mapper корректо прозводит конвертацию листа сущностей в лист DTO,
//	 * и корректно переносит их поля.
//	 */
//	@Test
//	void listTaskAttachmentToTaskAttachmentDTO() {
//		TaskAttachment attachment = new TaskAttachment();
//		attachment.setId(5L);
//
//		LinkedList<TaskAttachment> list = new LinkedList<>();
//		list.add(attachment);
//		list.add(attachment);
//
//		List<TaskAttachmentDTO> dtoList = mapper.listTaskAttachmentToTaskAttachmentDTO(list);
//		assertEquals(dtoList.get(0).getId(), list.get(0).getId());
//		assertEquals(dtoList.get(1).getId(), list.get(1).getId());
//	}
//
//	/**
//	 * Проверка того, что mapper корректо прозводит конвертацию листа DTO в лист сущностей,
//	 * и корректно переносит их поля.
//	 */
//	@Test
//	void listTaskAttachmentDTOToTaskAttachment() {
//		TaskAttachmentDTO dto = new TaskAttachmentDTO();
//		dto.setId(3L);
//
//		LinkedList<TaskAttachmentDTO> listDto = new LinkedList<>();
//		listDto.add(dto);
//		listDto.add(dto);
//
//		List<TaskAttachment> list = mapper.listTaskAttachmentDTOToTaskAttachment(listDto);
//		assertEquals(list.get(0).getId(), listDto.get(0).getId());
//		assertEquals(list.get(1).getId(), listDto.get(1).getId());
//	}
}