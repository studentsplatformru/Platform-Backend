package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.university.DisciplineDTO;
import ru.studentsplatform.backend.endpoint.mapper.DisciplineMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.DisciplineCRUDController;
import ru.studentsplatform.backend.service.crud.DisciplineService;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

/**
 * @author Archie-Vian (sas-aramonov@yandex.ru) 01.08.2020
 */
@Profiled
@RestController
@RequestMapping(DisciplineCRUDController.BASE_URL)
public class DisciplineCRUDControllerImpl implements DisciplineCRUDController {

	private final DisciplineMapper mapper;
	private final DisciplineService service;

	/**
	 * Конструктор.
	 *
	 * @param mapper  маппер discipline
	 * @param service CRUD сервис discipline
	 */
	public DisciplineCRUDControllerImpl(DisciplineMapper mapper, DisciplineService service) {
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<DisciplineDTO> create(DisciplineDTO disciplineDTO) {
		var entity = mapper.disciplineDTOToDiscipline(disciplineDTO);
		var result = mapper.disciplineToDisciplineDTO(service.create(entity));
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<DisciplineDTO> getById(Long id) {
		var result = mapper.disciplineToDisciplineDTO(service.getById(id));
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<DisciplineDTO>> getAll() {
		var result = mapper.listDisciplineToDisciplineDTO(service.getAll());
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<DisciplineDTO> update(DisciplineDTO disciplineDTO, Long id) {
		var entity = mapper.disciplineDTOToDiscipline(disciplineDTO);
		var result = mapper.disciplineToDisciplineDTO(service.update(entity, id));
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
