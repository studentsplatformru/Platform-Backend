package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleUserCellDTO;
import ru.studentsplatform.backend.entities.model.enums.UniversityRoleEnum;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleCell;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.entities.model.user.User;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ScheduleUserCellMapperTest {

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    ScheduleUserCellMapper mapper = new ScheduleUserCellMapperImpl();

    /**
     * Проверка того, что маппер может создать DTO на основе сущности
     */
    @Test
    void scheduleUserCellToScheduleUserCellDTO(){
        ScheduleUserCell entity = new ScheduleUserCell();
        ScheduleCell cell = new ScheduleCell();
        cell.setId(1L);
        User user = new User();
        user.setId(2L);
        entity.setScheduleCell(cell);
        entity.setUser(user);
        entity.setUniversityRole(UniversityRoleEnum.STUDENT);
        ScheduleUserCellDTO dto = mapper.scheduleUserCellToScheduleUserCellDTO(entity);
        assertEquals(dto.getScheduleCellId(), entity.getScheduleCell().getId());
        assertEquals(dto.getUserId(), entity.getUser().getId());
        assertEquals(dto.getUniversityRole(), entity.getUniversityRole().toString());
    }

    /**
     * Проверка того, что маппер может создать сущность на основе DTO
     */
    @Test
    void scheduleUserCellDTOToScheduleUserCell(){
        ScheduleUserCellDTO dto = new ScheduleUserCellDTO();
        dto.setUserId(1L);
        dto.setScheduleCellId(2L);
        dto.setUniversityRole("STUDENT");
        ScheduleUserCell entity = mapper.scheduleUserCellDTOToScheduleUserCell(dto);
        assertEquals(dto.getScheduleCellId(), entity.getScheduleCell().getId());
        assertEquals(dto.getUserId(), entity.getUser().getId());
        assertEquals(dto.getUniversityRole(), entity.getUniversityRole().toString());
    }

    /**
     * Проверка того, что маппер способен создать лист DTO на основе листа сущностей
     */
    @Test
    void listScheduleUserCellToScheduleUserCellDTOTest(){
        List<ScheduleUserCell> scheduleUserCellList = new LinkedList<>();
        ScheduleUserCell testScheduleUserCell = new ScheduleUserCell();
        testScheduleUserCell.setId(3L);
        scheduleUserCellList.add(testScheduleUserCell);
        scheduleUserCellList.add(testScheduleUserCell);
        List<ScheduleUserCellDTO> scheduleUserCellDTOS = mapper.listScheduleUserCellToScheduleUserCellDTO(scheduleUserCellList);
        assertEquals(3L, scheduleUserCellDTOS.get(0).getId());
        assertEquals(3L, scheduleUserCellDTOS.get(1).getId());
    }

    /**
     * Проверка того, что маппер способен создать лист сущностей на основе листа DTO
     */
    @Test
    void listScheduleUserCellDTOtoScheduleUserCellTest() {
        List<ScheduleUserCellDTO> scheduleUserCellDTOS = new LinkedList<>();
        ScheduleUserCellDTO testScheduleUserCellDTO = new ScheduleUserCellDTO();
        testScheduleUserCellDTO.setId(5L);
        scheduleUserCellDTOS.add(testScheduleUserCellDTO);
        scheduleUserCellDTOS.add(testScheduleUserCellDTO);
        List<ScheduleUserCell> scheduleUserCells = mapper.listScheduleUserCellDTOToScheduleUserCell(scheduleUserCellDTOS);
        assertEquals(5L, scheduleUserCells.get(0).getId());
        assertEquals(5L, scheduleUserCells.get(1).getId());
    }

}
