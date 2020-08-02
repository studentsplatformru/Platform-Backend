package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.university.DisciplineDTO;
import ru.studentsplatform.backend.entities.model.university.Discipline;
import ru.studentsplatform.backend.entities.model.university.Subject;
import ru.studentsplatform.backend.entities.model.user.User;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DisciplineMapperTest {

    @Mock
    ScheduleUserCellMapper userCellMapper;

    @InjectMocks
    DisciplineMapper mapper = new DisciplineMapperImpl();

    /**
     * Проверка того, что маппер может создать DTO на основе сущности
     */
    @Test
    void disciplineToDisciplineDTOTest(){
        Discipline entity = new Discipline();
        User user = new User();
        Subject subject = new Subject();
        subject.setId(3L);
        user.setId(2L);
        entity.setId(1L);
        entity.setSemester(1);
        entity.setCreatedBy("test");
        entity.setUser(user);
        entity.setSubject(subject);
        DisciplineDTO dto = mapper.disciplineToDisciplineDTO(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getSemester(), entity.getSemester());
        assertEquals(dto.getUserId(), entity.getUser().getId());
        assertEquals(dto.getSubjectId(), entity.getSubject().getId());

    }

    /**
     * Проверка того, что маппер способен создать сущность на основе DTO
     */
    @Test
    void disciplineDTOToDisciplineTest(){
        DisciplineDTO dto = new DisciplineDTO();
        dto.setId(1L);
        dto.setSemester(2);
        dto.setSubjectId(3L);
        dto.setUserId(4L);
        dto.setCreatedBy("test");
        Discipline entity = mapper.disciplineDTOToDiscipline(dto);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getSemester(), entity.getSemester());
        assertEquals(dto.getUserId(), entity.getUser().getId());
        assertEquals(dto.getSubjectId(), entity.getSubject().getId());
    }

    /**
     * Проверка того, что маппер способен создать лист DTO на основе листа сущностей
     */
    @Test
    void listDisciplineToDisciplineDTOTest(){
        List<Discipline> disciplineList = new LinkedList<>();
        Discipline testDiscipline = new Discipline();
        testDiscipline.setId(3L);
        disciplineList.add(testDiscipline);
        disciplineList.add(testDiscipline);
        List<DisciplineDTO> disciplineDTOS = mapper.listDisciplineToDisciplineDTO(disciplineList);
        assertEquals(3L, disciplineDTOS.get(0).getId());
        assertEquals(3L, disciplineDTOS.get(1).getId());
    }

    /**
     * Проверка того, что маппер способен создать лист сущностей на основе листа DTO
     */
    @Test
    void listDisciplineDTOtoDisciplineTest() {
        List<DisciplineDTO> disciplineDTOS = new LinkedList<>();
        DisciplineDTO testDisciplineDTO = new DisciplineDTO();
        testDisciplineDTO.setId(5L);
        disciplineDTOS.add(testDisciplineDTO);
        disciplineDTOS.add(testDisciplineDTO);
        List<Discipline> disciplines = mapper.listDisciplineDTOToDiscipline(disciplineDTOS);
        assertEquals(5L, disciplines.get(0).getId());
        assertEquals(5L, disciplines.get(1).getId());
    }
}
