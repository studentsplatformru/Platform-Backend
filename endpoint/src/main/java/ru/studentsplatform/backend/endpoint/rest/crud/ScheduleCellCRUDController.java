package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleCellDTO;

import java.util.List;

public interface ScheduleCellCRUDController extends AbstractCRUDController<ScheduleCellDTO> {
	String BASE_URL = AbstractCRUDController.BASE_URL + "/schedule_cell";

	@Override
	ResponseEntity<ScheduleCellDTO> create(@RequestBody ScheduleCellDTO scheduleCellDTO);

	@Override
	ResponseEntity<ScheduleCellDTO> getById(@PathVariable Long id);

	@Override
	ResponseEntity<List<ScheduleCellDTO>> getAll();

	@Override
	ResponseEntity<ScheduleCellDTO> update(@RequestBody ScheduleCellDTO scheduleCellDTO, @PathVariable Long id);

	@Override
	ResponseEntity<Boolean> delete(@PathVariable Long id);
}
