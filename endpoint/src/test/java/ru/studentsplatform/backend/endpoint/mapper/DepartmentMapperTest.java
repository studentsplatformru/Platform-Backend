package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.university.DepartmentDTO;
import ru.studentsplatform.backend.entities.model.university.Department;
import ru.studentsplatform.backend.entities.model.university.Faculty;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentMapperTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private DepartmentMapper mapper = new DepartmentMapperImpl();


    @Test
    void departmentToDepartmentDTOTest(){
        Department department = new Department();
        Faculty faculty = new Faculty();
        faculty.setId(2L);
        department.setId(1L);
        department.setDepartment("test");
        department.setFaculty(faculty);

        DepartmentDTO dto = mapper.departmentToDepartmentDTO(department);
        assertEquals(dto.getId(), department.getId());
        assertEquals(dto.getDepartment(), department.getDepartment());
        assertEquals(dto.getFacultyId(), department.getFaculty().getId());

    }

    @Test
    void departmentDTOToDepartmentTest(){
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(2L);
        dto.setFacultyId(1L);
        dto.setDepartment("test");
        Department department = mapper.departmentDTOToDepartment(dto);
        assertEquals(department.getId(), dto.getId());
        assertEquals(department.getDepartment(), dto.getDepartment());
        assertEquals(department.getFaculty().getId(), dto.getFacultyId());
    }

}
