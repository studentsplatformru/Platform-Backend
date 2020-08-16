package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.university.FacultyDTO;
import ru.studentsplatform.backend.endpoint.mapper.FacultyMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.FacultyCRUDController;
import ru.studentsplatform.backend.service.crud.FacultyService;
import ru.studentsplatform.backend.system.exception.core.Fault;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

import static ru.studentsplatform.backend.system.helper.ControllerUtils.handleExceptions;

/**
 * @author Archie-Vian (sas-aramonov@yandex.ru) 01.08.2020
 */
@Profiled
@RestController
@RequestMapping(FacultyCRUDController.BASE_URL)
public class FacultyCRUDControllerImpl implements FacultyCRUDController {

	private final FacultyMapper mapper;
	private final FacultyService service;

	/**
	 * Конструктор.
	 *
	 * @param mapper  Маппер faculty
	 * @param service CRUD сервис faculty
	 */
	public FacultyCRUDControllerImpl(FacultyMapper mapper, FacultyService service) {
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<FacultyDTO> create(FacultyDTO facultyDTO) {
		var entity = mapper.facultyDTOToFaculty(facultyDTO);
		var result = mapper.facultyToFacultyDTO(service.create(entity));
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<FacultyDTO> getById(Long id) throws Fault {
		return handleExceptions(() -> ResponseEntity.ok(mapper.facultyToFacultyDTO(service.getById(id))));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<FacultyDTO>> getAll() {
		var result = mapper.listFacultyToFacultyDTO(service.getAll());
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<FacultyDTO> update(FacultyDTO facultyDTO, Long id) {
		var entity = mapper.facultyDTOToFaculty(facultyDTO);
		var result = mapper.facultyToFacultyDTO(service.update(entity, id));
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Boolean> delete(Long id) {
		return ResponseEntity.ok(service.delete(id));
	}
}
