package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleUserCellDTO;
import ru.studentsplatform.backend.endpoint.mapper.ScheduleUserCellMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.ScheduleUserCellController;
import ru.studentsplatform.backend.service.crud.impl.ScheduleUserCellServiceImpl;
import ru.studentsplatform.backend.system.annotation.Profiled;

import java.util.List;

@Profiled
@RestController
@RequestMapping(ScheduleUserCellController.BASE_URL)
public class ScheduleUserCellControllerImpl implements ScheduleUserCellController {
    private final ScheduleUserCellMapper scheduleUserCellMapper;

    private final ScheduleUserCellServiceImpl scheduleUserCellService;

    /**
     * Конструктор.
     * @param scheduleUserCellMapper маппер, преобразующий ScheduleUserCellDTO в сущность ScheduleUserCell и наоборот.
     * @param scheduleUserCellService CRUD сервис ScheduleUserCell.
     */
    public ScheduleUserCellControllerImpl(ScheduleUserCellMapper scheduleUserCellMapper,
                                      ScheduleUserCellServiceImpl scheduleUserCellService) {
        this.scheduleUserCellMapper = scheduleUserCellMapper;
        this.scheduleUserCellService = scheduleUserCellService;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<ScheduleUserCellDTO> create(ScheduleUserCellDTO dto) {
        var scheduleUserCell = scheduleUserCellMapper.scheduleUserCellDTOToScheduleUserCell(dto);
        scheduleUserCell = scheduleUserCellService.create(scheduleUserCell);
        var result = scheduleUserCellMapper.scheduleUserCellToScheduleUserCellDTO(scheduleUserCell);
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<ScheduleUserCellDTO> getById(Long id) {
        return ResponseEntity.ok(scheduleUserCellMapper.scheduleUserCellToScheduleUserCellDTO(scheduleUserCellService.getById(id)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<List<ScheduleUserCellDTO>> getAll() {
        return ResponseEntity.ok(scheduleUserCellMapper.listScheduleUserCellToScheduleUserCellDTO(scheduleUserCellService.getAll()));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<ScheduleUserCellDTO> update(ScheduleUserCellDTO dto, Long id) {
        var entity = scheduleUserCellMapper.scheduleUserCellDTOToScheduleUserCell(dto);
        return ResponseEntity.ok(scheduleUserCellMapper
                .scheduleUserCellToScheduleUserCellDTO(scheduleUserCellService.update(entity, id)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(scheduleUserCellService.delete(id));
    }
}
