package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.UserDTO;
import ru.studentsplatform.backend.entities.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "firstName", source = "entity.firstName"),
            @Mapping(target = "lastName", source = "entity.lastName"),
            @Mapping(target = "patronymic", source = "entity.patronymic"),
            @Mapping(target = "phoneNumber", source = "entity.phoneNumber"),
            @Mapping(target = "email", source = "entity.email"),
            @Mapping(target = "password", source = "entity.password"),
            @Mapping(target = "teacher", source = "entity.teacher"),
            @Mapping(target = "student", source = "entity.student"),
            @Mapping(target = "teachersFeedback", source = "entity.teachersFeedback")
    })
    UserDTO userToUserDTO(User entity);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "firstName", source = "dto.firstName"),
            @Mapping(target = "lastName", source = "dto.lastName"),
            @Mapping(target = "patronymic", source = "dto.patronymic"),
            @Mapping(target = "phoneNumber", source = "dto.phoneNumber"),
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "password", source = "dto.password"),
            @Mapping(target = "teacher", source = "dto.teacher"),
            @Mapping(target = "student", source = "dto.student"),
            @Mapping(target = "teachersFeedback", source = "dto.teachersFeedback")
    })
    User userDTOtoUser(UserDTO dto);

    List<UserDTO> listUserToUserDTO(List<User> entity);

    List<User> listUserDTOtoUser(List<UserDTO> dto);

}
