package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.Attendance;
import ru.studentsplatform.backend.repository.AttendanceRepository;
import ru.studentsplatform.backend.service.crud.AttendanceService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Attendance create(Attendance newEntity) {
        return attendanceRepository.saveAndFlush(newEntity);
    }

    @Override
    public Attendance getById(Long id) {
        return attendanceRepository.findById(id).orElseThrow(NoSuchElementException::new);

    }

    @Override
    public List<Attendance> getAll() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance update(Attendance updatedEntity, Long id) {
        updatedEntity.setId(id);
        return attendanceRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        attendanceRepository.deleteById(id);
        return true;
    }
}
