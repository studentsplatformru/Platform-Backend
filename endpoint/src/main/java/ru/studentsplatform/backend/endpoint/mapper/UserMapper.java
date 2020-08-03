package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.user.UserDTO;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

@Profiled
@Mapper(componentModel = "spring", uses = {DisciplineMapper.class})
public interface UserMapper {
	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "email", source = "entity.email"),
			@Mapping(target = "password", source = "entity.password"),
			@Mapping(target = "userInfoId", source = "entity.userInfo.id"),
			@Mapping(target = "universityRole", source = "entity.universityRole"),
			@Mapping(target = "placeStudyId", source = "entity.placeStudy.id"),
			@Mapping(target = "teamId", source = "entity.team.id"),
			@Mapping(target = "disciplines", source = "entity.disciplines")
	})
	UserDTO userToUserDTO(User entity);

	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
			@Mapping(target = "createdBy", source = "dto.createdBy"),
			@Mapping(target = "email", source = "dto.email"),
			@Mapping(target = "password", source = "dto.password"),
			@Mapping(target = "userInfo.id", source = "dto.userInfoId"),
			@Mapping(target = "universityRole", source = "dto.universityRole"),
			@Mapping(target = "team.id", source = "dto.teamId"),
			@Mapping(target = "disciplines", source = "dto.disciplines")
	})
	User userDTOtoUser(UserDTO dto);

	List<UserDTO> listUserToUserDTO(List<User> entity);

	List<User> listUserDTOtoUser(List<UserDTO> dto);
}
