package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleUserCellDTO;

import java.util.List;

public interface ScheduleUserCellCRUDController extends AbstractCRUDController<ScheduleUserCellDTO> {
	String BASE_URL = AbstractCRUDController.BASE_URL + "/schedule_user_cell";

	@Override
	ResponseEntity<ScheduleUserCellDTO> create(@RequestBody ScheduleUserCellDTO scheduleUserCellDTO);

	@Override
	ResponseEntity<ScheduleUserCellDTO> getById(@PathVariable Long id);

	@Override
	ResponseEntity<List<ScheduleUserCellDTO>> getAll();

	@Override
	ResponseEntity<ScheduleUserCellDTO> update(@RequestBody ScheduleUserCellDTO scheduleUserCellDTO,
											   @PathVariable Long id);

	@Override
	ResponseEntity<Boolean> delete(@PathVariable Long id);
}
