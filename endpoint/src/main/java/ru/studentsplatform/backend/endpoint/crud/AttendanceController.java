package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.AttendanceDTO;

public interface AttendanceController extends AbstractController<AttendanceDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/attendance";
}
