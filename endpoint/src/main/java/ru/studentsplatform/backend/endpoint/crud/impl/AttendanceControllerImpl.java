package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.AttendanceDTO;
import ru.studentsplatform.backend.endpoint.crud.AttendanceController;
import ru.studentsplatform.backend.entities.model.Attendance;
import ru.studentsplatform.backend.mapper.AttendanceMapper;
import ru.studentsplatform.backend.service.crud.AttendanceService;

import java.util.List;

@RestController
@RequestMapping(AttendanceController.BASE_URL)
public class AttendanceControllerImpl implements AttendanceController {

    private final AttendanceService attendanceService;

    private final AttendanceMapper mapper;

    public AttendanceControllerImpl(AttendanceService attendanceService, AttendanceMapper mapper) {
        this.attendanceService = attendanceService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<AttendanceDTO> create(AttendanceDTO newInstanceRequest) {
        Attendance attendance = mapper.attendanceDTOtoAttendance(newInstanceRequest);
        attendance = attendanceService.create(attendance);
        return ResponseEntity.ok(mapper.attendanceToAttendanceDTO(attendance));
    }

    @Override
    public ResponseEntity<AttendanceDTO> getById(Long id) {
        Attendance attendance = attendanceService.getById(id);
        AttendanceDTO attendanceDTO = mapper.attendanceToAttendanceDTO(attendance);
        return ResponseEntity.ok(attendanceDTO);
    }

    @Override
    public ResponseEntity<List<AttendanceDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<AttendanceDTO> update(AttendanceDTO updatedInstanceRequest, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return null;
    }
}
