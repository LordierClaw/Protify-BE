package com.proptit.protify_be.controller;

import com.proptit.protify_be.dto.UserBasicInfoDto;
import com.proptit.protify_be.dto.UserRegisterDto;
import com.proptit.protify_be.dto.UserUpdateInfoDto;
import com.proptit.protify_be.entity.UserEntity;
import com.proptit.protify_be.exception.ResponseException;
import com.proptit.protify_be.mapper.UserMapper;
import com.proptit.protify_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserBasicInfoDto> addUser(@RequestBody UserRegisterDto userRegisterDto) {
        try {
            UserEntity entity = userService.insertUser(userRegisterDto);
            return ResponseEntity.ok(userMapper.toUserBasicInfoDto(entity));
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserBasicInfoDto>> getAllUsers() {
        try {
            List<UserEntity> userEntities = (List<UserEntity>) userService.getAllUser();
            return ResponseEntity.ok(userEntities.stream().map(userMapper::toUserBasicInfoDto).toList());
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserBasicInfoDto> getUserById(@PathVariable(name = "userId") Long userId) {
        try {
            UserEntity entity = userService.getUserById(userId);
            return ResponseEntity.ok(userMapper.toUserBasicInfoDto(entity));
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<UserBasicInfoDto> updateUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authz,
            @PathVariable(name = "userId") Long userId,
            @RequestBody UserUpdateInfoDto userUpdateDto
    ) throws ResponseException {
        String accessToken = authz.replace("Bearer ", "");
        UserEntity entity = userService.updateUser(accessToken, userId, userUpdateDto);
        return ResponseEntity.ok(userMapper.toUserBasicInfoDto(entity));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Long> deleteUserById(@PathVariable(name = "userId") Long userId) {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok(userId);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<List<Long>> deleteAllUsersById(@RequestParam(name = "id") Long[] ids) {
        try {
            userService.deleteAllUserByIds(List.of(ids));
            return ResponseEntity.ok(List.of(ids));
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }
}
