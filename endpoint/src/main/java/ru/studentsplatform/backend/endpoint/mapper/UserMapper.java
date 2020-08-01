package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.user.UserDTO;
import ru.studentsplatform.backend.entities.model.user.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "email", source = "entity.email"),
            @Mapping(target = "password", source = "entity.password")
    })
    UserDTO userToUserDTO(User entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "password", source = "dto.password")
    })
    User userDTOtoUser(UserDTO dto);

    List<UserDTO> listUserToUserDTO(List<User> entity);
    List<User> listUserDTOtoUser(List<UserDTO> dto);
}
