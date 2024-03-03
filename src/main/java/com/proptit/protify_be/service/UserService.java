package com.proptit.protify_be.service;

import com.proptit.protify_be.dto.UserRegisterDto;
import com.proptit.protify_be.dto.UserUpdateInfoDto;
import com.proptit.protify_be.dto.UserUpdatePasswordDto;
import com.proptit.protify_be.entity.UserEntity;
import com.proptit.protify_be.exception.ResponseException;

public interface UserService {
    UserEntity insertUser(UserRegisterDto userRegisterDto) throws ResponseException;
    UserEntity updateUser(String accessToken, Long id, UserUpdateInfoDto userUpdateInfoDto) throws ResponseException;
    UserEntity updateUser(String accessToken, Long id, UserUpdatePasswordDto userUpdatePasswordDto) throws ResponseException;
    void deleteUserById(Long id) throws ResponseException;
    void deleteAllUserByIds(Iterable<Long> ids) throws ResponseException;
    Iterable<UserEntity> getAllUser() throws ResponseException;
    UserEntity getUserById(Long id) throws ResponseException;
    UserEntity getUserByEmail(String email) throws ResponseException;
}
