package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.domain.dto.user.UserInfoDTO;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.entities.model.user.UserInfo;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserInfoMapperTest {

	private UserInfoMapperImpl mapper = new UserInfoMapperImpl();

	@Test
	void userInfoToUserInfoDTOTest() {
		UserInfo userInfo = new UserInfo();
		User user = new User();
		user.setId(Long.valueOf(1));
		userInfo.setUser(user);
		userInfo.setCreatedBy("created_by");
		userInfo.setModifiedBy("modified_by");
		userInfo.setFirstName("first_name");
		userInfo.setLastName("last_name");
		userInfo.setPatronymic("patro");
		userInfo.setPhoneNumber("phone");

		UserInfoDTO dto = mapper.userInfoToUserInfoDTO(userInfo);
		Assert.assertEquals(userInfo.getUser().getId(), dto.getUserId());
		Assert.assertEquals(userInfo.getModifiedBy(), dto.getModifiedBy());
		Assert.assertEquals(userInfo.getFirstName(), dto.getFirstName());
		Assert.assertEquals(userInfo.getLastName(), dto.getLastName());
		Assert.assertEquals(userInfo.getPatronymic(), dto.getPatronymic());
		Assert.assertEquals(userInfo.getPhoneNumber(), dto.getPhoneNumber());
	}

	@Test
	void userInfoDTOtoUserInfoTest() {
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setUserId(Long.valueOf(1));
		userInfoDTO.setCreatedBy("created_by");
		userInfoDTO.setModifiedBy("modified_by");
		userInfoDTO.setFirstName("first_name");
		userInfoDTO.setLastName("last_name");
		userInfoDTO.setPatronymic("patro");
		userInfoDTO.setPhoneNumber("phone");

		UserInfo entity = mapper.userInfoDTOtoUserInfo(userInfoDTO);
		Assert.assertEquals(userInfoDTO.getUserId(), entity.getUser().getId());
		Assert.assertEquals(userInfoDTO.getModifiedBy(), entity.getModifiedBy());
		Assert.assertEquals(userInfoDTO.getFirstName(), entity.getFirstName());
		Assert.assertEquals(userInfoDTO.getLastName(), entity.getLastName());
		Assert.assertEquals(userInfoDTO.getPatronymic(), entity.getPatronymic());
		Assert.assertEquals(userInfoDTO.getPhoneNumber(), entity.getPhoneNumber());

	}

	@Test
	void listUserInfoToUserInfoDTOTest() {
		List<UserInfo> userInfoList = new LinkedList<>();
		UserInfo testUserInfo = new UserInfo();
		testUserInfo.setId(3L);
		userInfoList.add(testUserInfo);
		userInfoList.add(testUserInfo);
		List<UserInfoDTO> userInfoDTOS = mapper.listUserInfoToUserInfoDTO(userInfoList);
		assertEquals(3L, userInfoDTOS.get(0).getId());
		assertEquals(3L, userInfoDTOS.get(1).getId());
	}

	@Test
	void listUserInfoDTOtoUserInfoTest() {
		List<UserInfoDTO> userInfoDTOS = new LinkedList<>();
		UserInfoDTO testUserInfoDTO = new UserInfoDTO();
		testUserInfoDTO.setId(5L);
		userInfoDTOS.add(testUserInfoDTO);
		userInfoDTOS.add(testUserInfoDTO);
		List<UserInfo> userInfos = mapper.listUserInfoDTOtoUserInfo(userInfoDTOS);
		assertEquals(5L, userInfos.get(0).getId());
		assertEquals(5L, userInfos.get(1).getId());
	}
}