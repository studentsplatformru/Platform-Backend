package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.HomeworkDTO;
import ru.studentsplatform.backend.endpoint.crud.AttendanceController;
import ru.studentsplatform.backend.endpoint.crud.HomeworkController;
import ru.studentsplatform.backend.entities.model.Homework;
import ru.studentsplatform.backend.mapper.HomeworkMapper;
import ru.studentsplatform.backend.service.crud.HomeworkService;
import java.util.List;

@RestController
@RequestMapping(AttendanceController.BASE_URL)
public class HomeworkControllerImpl implements HomeworkController {

    private final HomeworkService homeworkService;

    private final HomeworkMapper mapper;

    public HomeworkControllerImpl(HomeworkService homeworkService, HomeworkMapper mapper) {
        this.homeworkService = homeworkService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<HomeworkDTO> create(HomeworkDTO newInstanceRequest) {
        Homework homework = mapper.homeworkDTOtoHomework(newInstanceRequest);
        homework = homeworkService.create(homework);
        return ResponseEntity.ok(mapper.homeworkToHomeworkDTO(homework));
    }

    @Override
    public ResponseEntity<HomeworkDTO> getById(Long id) {
        Homework homework = homeworkService.getById(id);
        HomeworkDTO homeworkDTO = mapper.homeworkToHomeworkDTO(homework);
        return ResponseEntity.ok(homeworkDTO);
    }

    @Override
    public ResponseEntity<List<HomeworkDTO>> getAll() {
        List<Homework> homeworkList = homeworkService.getAll();
        List<HomeworkDTO> homeworkDTOList = mapper.listHomeworkToHomeworkDTO(homeworkList);
        return ResponseEntity.ok(homeworkDTOList);
    }

    @Override
    public ResponseEntity<HomeworkDTO> update(HomeworkDTO updatedInstanceRequest, Long id) {
        Homework homework = mapper.homeworkDTOtoHomework(updatedInstanceRequest);
        homework = homeworkService.update(homework, id);
        return ResponseEntity.ok(mapper.homeworkToHomeworkDTO(homework));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(homeworkService.delete(id));
    }
}
