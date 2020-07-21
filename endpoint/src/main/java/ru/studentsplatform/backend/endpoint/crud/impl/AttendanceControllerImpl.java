package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.AttendanceDTO;
import ru.studentsplatform.backend.endpoint.crud.AttendanceController;
import ru.studentsplatform.backend.service.crud.AttendanceService;

import java.util.List;

@RestController
@RequestMapping(AttendanceController.BASE_URL)
public class AttendanceControllerImpl implements AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceControllerImpl(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @Override
    public ResponseEntity<AttendanceDTO> create(AttendanceDTO newInstanceRequest) {
        return null;
    }

    @Override
    public ResponseEntity<AttendanceDTO> getById(Long id) {
        return null;
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
