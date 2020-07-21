package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.TeachersFeedbackDTO;

public interface TeachersFeedbackController extends AbstractController<TeachersFeedbackDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/teachers_feedback";
}
