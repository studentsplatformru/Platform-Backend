package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.Attendance;
import ru.studentsplatform.backend.mapper.AttendanceMapper;
import ru.studentsplatform.backend.repository.AttendanceRepository;
import ru.studentsplatform.backend.service.crud.AttendanceService;

import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper mapper;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.mapper = attendanceMapper;
    }

    @Override
    public Attendance create(Attendance newEntity) {
        return null;
    }

    @Override
    public Attendance getById(Long id) {
        return null;
    }

    @Override
    public Attendance getByCreator(String creator) {
        return null;
    }

    @Override
    public Attendance getByLastModifier(String modificatedBy) {
        return null;
    }

    @Override
    public List<Attendance> getAll() {
        return null;
    }

    @Override
    public Attendance update(Attendance updatedEntity, Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
