package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleCellDTO;
import ru.studentsplatform.backend.endpoint.mapper.ScheduleCellMapper;
import ru.studentsplatform.backend.endpoint.rest.ScheduleCellController;
import ru.studentsplatform.backend.service.crud.impl.ScheduleCellServiceImpl;
import ru.studentsplatform.backend.system.annotation.Profiled;

import java.util.List;

@Profiled
@RestController
@RequestMapping(ScheduleCellController.BASE_URL)
public class ScheduleCellControllerImpl implements ScheduleCellController {
    private final ScheduleCellMapper scheduleCellMapper;

    private final ScheduleCellServiceImpl scheduleCellService;

    /**
     * Конструктор.
     * @param scheduleCellMapper маппер, преобразующий ScheduleCellDTO в сущность ScheduleCell и наоборот.
     * @param scheduleCellService CRUD сервис ScheduleCell.
     */
    public ScheduleCellControllerImpl(ScheduleCellMapper scheduleCellMapper,
                                      ScheduleCellServiceImpl scheduleCellService) {
        this.scheduleCellMapper = scheduleCellMapper;
        this.scheduleCellService = scheduleCellService;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<ScheduleCellDTO> create(ScheduleCellDTO dto) {
        var scheduleCell = scheduleCellMapper.scheduleCellDTOToScheduleCell(dto);
        scheduleCell = scheduleCellService.create(scheduleCell);
        var result = scheduleCellMapper.scheduleCellToScheduleCellDTO(scheduleCell);
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<ScheduleCellDTO> getById(Long id) {
        return ResponseEntity.ok(scheduleCellMapper.scheduleCellToScheduleCellDTO(scheduleCellService.getById(id)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<List<ScheduleCellDTO>> getAll() {
        return ResponseEntity.ok(scheduleCellMapper.listScheduleCellToScheduleCellDTO(scheduleCellService.getAll()));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<ScheduleCellDTO> update(ScheduleCellDTO dto, Long id) {
        var entity = scheduleCellMapper.scheduleCellDTOToScheduleCell(dto);
        return ResponseEntity.ok(scheduleCellMapper
                .scheduleCellToScheduleCellDTO(scheduleCellService.update(entity, id)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(scheduleCellService.delete(id));
    }
}
