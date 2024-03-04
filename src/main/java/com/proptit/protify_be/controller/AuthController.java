package com.proptit.protify_be.controller;

import com.proptit.protify_be.dto.UserAllTokenDto;
import com.proptit.protify_be.dto.UserBasicInfoDto;
import com.proptit.protify_be.dto.UserLoginDto;
import com.proptit.protify_be.dto.UserRegisterDto;
import com.proptit.protify_be.exception.ResponseException;
import com.proptit.protify_be.mapper.UserMapper;
import com.proptit.protify_be.service.AuthenticationService;
import com.proptit.protify_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public AuthController(AuthenticationService authenticationService, UserService userService, UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<UserAllTokenDto> login(@RequestBody UserLoginDto userLoginDto) throws ResponseException {
        return ResponseEntity.ok(authenticationService.validateAndGetToken(userLoginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserBasicInfoDto> register(@RequestBody UserRegisterDto userRegisterDto) throws ResponseException {
        return ResponseEntity.ok(userMapper.toUserBasicInfoDto(userService.insertUser(userRegisterDto)));
    }
}
