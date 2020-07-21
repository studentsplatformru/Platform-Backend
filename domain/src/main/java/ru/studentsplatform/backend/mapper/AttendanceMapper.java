package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.AttendanceDTO;
import ru.studentsplatform.backend.entities.model.Attendance;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "presence", source = "entity.presence"),
            @Mapping(target = "lesson", source = "entity.lesson")
    })
    AttendanceDTO attendanceToAttendanceDTO(Attendance entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "presence", source = "dto.presence"),
            @Mapping(target = "lesson", source = "dto.lesson")
    })
    Attendance attendanceDTOtoAttendance(AttendanceDTO dto);

    List<AttendanceDTO> listAttendanceToAttendanceDTO(List<Attendance> entity);

    List<Attendance> listAttendanceDTOtoAttendance(List<AttendanceDTO> dto);
}
