package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.university.DepartmentDTO;
import ru.studentsplatform.backend.endpoint.mapper.DepartmentMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.DepartmentCRUDController;
import ru.studentsplatform.backend.service.crud.DepartmentService;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

/**
 * @author Archie-Vian (sas-aramonov@yandex.ru) 01.08.2020
 */
@Profiled
@RestController
@RequestMapping(DepartmentCRUDController.BASE_URL)
public class DepartmentCRUDControllerImpl implements DepartmentCRUDController {

	private final DepartmentMapper mapper;
	private final DepartmentService service;

	/**
	 * Конструктор.
	 *
	 * @param mapper  маппер department
	 * @param service CRUD сервис department
	 */
	public DepartmentCRUDControllerImpl(DepartmentMapper mapper, DepartmentService service) {
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<DepartmentDTO> create(DepartmentDTO departmentDTO) {
		var entity = mapper.departmentDTOToDepartment(departmentDTO);
		var result = mapper.departmentToDepartmentDTO(service.create(entity));
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<DepartmentDTO> getById(Long id) {
		var result = mapper.departmentToDepartmentDTO(service.getById(id));
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<DepartmentDTO>> getAll() {
		var result = mapper.listDepartmentToDepartmentDTO(service.getAll());
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<DepartmentDTO> update(DepartmentDTO departmentDTO, Long id) {
		var entity = mapper.departmentDTOToDepartment(departmentDTO);
		var result = mapper.departmentToDepartmentDTO(service.update(entity, id));
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
