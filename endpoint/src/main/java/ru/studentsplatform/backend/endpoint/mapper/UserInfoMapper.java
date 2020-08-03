package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.user.UserInfoDTO;
import ru.studentsplatform.backend.entities.model.user.UserInfo;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

@Profiled
@Mapper(componentModel = "spring")
public interface UserInfoMapper {
	@Mappings({
			@Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "userId", source = "entity.user.id"),
			@Mapping(target = "firstName", source = "entity.firstName"),
			@Mapping(target = "lastName", source = "entity.lastName"),
			@Mapping(target = "patronymic", source = "entity.patronymic"),
			@Mapping(target = "phoneNumber", source = "entity.phoneNumber")
	})
	UserInfoDTO userInfoToUserInfoDTO(UserInfo entity);

	@Mappings({
			@Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
			@Mapping(target = "createdBy", source = "dto.createdBy"),
			@Mapping(target = "user.id", source = "dto.userId"),
			@Mapping(target = "firstName", source = "dto.firstName"),
			@Mapping(target = "lastName", source = "dto.lastName"),
			@Mapping(target = "patronymic", source = "dto.patronymic"),
			@Mapping(target = "phoneNumber", source = "dto.phoneNumber")
	})
	UserInfo userInfoDTOtoUserInfo(UserInfoDTO dto);

	List<UserInfoDTO> listUserInfoToUserInfoDTO(List<UserInfo> entity);

	List<UserInfo> listUserInfoDTOtoUserInfo(List<UserInfoDTO> dto);
}
