package ru.studentsplatform.backend.service.crud;

import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;

import java.util.List;

/**
 * CRUD сервис для приложенных к task фалам.
 *
 * @author Krylov Sergey (26.07.2020)
 */
public interface TaskAttachmentService extends AbstractService<TaskAttachment> {

	/**
	 * Сохранение прикрепления к задаче в БД. Связь с задачей должна быть прописана заранее!
	 *
	 * @param newEntity Прикрепление к задаче, которое буде сохранено
	 * @return Сохрененное прикрепление к задаче
	 */
	@Override
	TaskAttachment create(TaskAttachment newEntity);

	/**
	 * Поиск прикрепления к задаче по его Id.
	 *
	 * @param id Id прикрепления к задаче
	 * @return Прикрепление к задаче
	 */
	@Override
	TaskAttachment getById(Long id);

	/**
	 * Поиск всех прикреплений к задачам, находящихся в БД.
	 *
	 * @return Лист прикреплений к задаче
	 */
	@Override
	List<TaskAttachment> getAll();

	/**
	 * Обновление данных прикрепления к задаче.
	 *
	 * @param updatedEntity Обновленное прикрепление к задаче
	 * @param id            Id прикрепления, которое будет обновлено
	 * @return Обновленное прикрепление к задаче
	 */
	@Override
	TaskAttachment update(TaskAttachment updatedEntity, Long id);

	/**
	 * Удаление прикрепления к задаче.
	 *
	 * @param id Id прикрепления к задаче, которое должно быть удалено
	 * @return Успешно ли произведено удаление
	 */
	@Override
	boolean delete(Long id);

	/**
	 * Позоляет найти список файлов, прикрепленных к определенной задаче.
	 *
	 * @param taskId Id задачи, к которой прикреплены искомые файлы
	 * @return Файлы, относящиеся к задаче
	 */

	List<TaskAttachment> getByTaskId(Long taskId);

	/**
	 * Позволяет создать и схоранить прикрепление к задаче на основе файла.
	 *
	 * @param task Задача, к которой требуется прикрепить файл
	 * @param file Файл, который мы прикрепляем
	 * @return Прикрепление к задаче, которое мы создали и привязали
	 */
	TaskAttachment createByFile(Task task, MultipartFile file);

	/**
	 * Позволяет найти файл по его Id в
	 * списке прикрепленных к задаче файлов.
	 *
	 * @param taskId       Id задачи, к которой прикреплен искомый файл
	 * @param attachmentId Id рикрепления
	 * @return Искомый файл
	 */
	TaskAttachment getByFileId(Long taskId, Long attachmentId);

}
