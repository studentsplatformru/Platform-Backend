package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.user.UserInfoDTO;
import ru.studentsplatform.backend.endpoint.mapper.UserInfoMapper;
import ru.studentsplatform.backend.endpoint.rest.UserInfoController;
import ru.studentsplatform.backend.service.crud.impl.UserInfoServiceImpl;
import ru.studentsplatform.backend.system.annotation.Profiled;

import java.util.List;

@Profiled
@RestController
@RequestMapping(UserInfoController.BASE_URL)
public class UserInfoControllerImpl implements UserInfoController {
    private final UserInfoMapper userInfoMapper;

    private final UserInfoServiceImpl userInfoService;

    public UserInfoControllerImpl(UserInfoMapper userInfoMapper, UserInfoServiceImpl userInfoService) {
        this.userInfoMapper = userInfoMapper;
        this.userInfoService = userInfoService;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<UserInfoDTO> create(UserInfoDTO dto) {
        var userInfo = userInfoMapper.userInfoDTOtoUserInfo(dto);
        userInfo = userInfoService.create(userInfo);
        var result = userInfoMapper.userInfoToUserInfoDTO(userInfo);
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> uploadImage(Long userInfoId, MultipartFile file) {
        return ResponseEntity.ok(userInfoService.uploadImage(file, userInfoId));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<UserInfoDTO> getById(Long id) {
        return ResponseEntity.ok(userInfoMapper.userInfoToUserInfoDTO(userInfoService.getById(id)));
    }

    @Override
    public ResponseEntity<List<UserInfoDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<UserInfoDTO> update(UserInfoDTO updatedInstanceRequest, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return null;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<ByteArrayResource> getImage(Long userInfoId) {
        var userInfo = userInfoService.getById(userInfoId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(userInfo.getImgType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + userInfo.getImgName() + "\"")
                .body(new ByteArrayResource(userInfo.getImg()));
    }
}
