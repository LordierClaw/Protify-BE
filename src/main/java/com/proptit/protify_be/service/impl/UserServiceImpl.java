package com.proptit.protify_be.service.impl;

import com.proptit.protify_be.constants.ResponseValue;
import com.proptit.protify_be.dto.UserRegisterDto;
import com.proptit.protify_be.dto.UserUpdateInfoDto;
import com.proptit.protify_be.dto.UserUpdatePasswordDto;
import com.proptit.protify_be.entity.UserEntity;
import com.proptit.protify_be.exception.ResponseException;
import com.proptit.protify_be.mapper.UserMapper;
import com.proptit.protify_be.repository.UserRepository;
import com.proptit.protify_be.security.AuthManager;
import com.proptit.protify_be.service.UserService;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthManager authManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthManager authManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    private UserEntity verifyToken(String token, Long id) throws ResponseException {
        Claims claims = authManager.decodeToken(token);
        Date currentDate = new Date();
        if (claims.getIssuedAt().compareTo(currentDate) > 0) {
            throw new ResponseException(ResponseValue.EXPIRED_TOKEN);
        }
        try {
            long tokenId = Long.parseLong(claims.getId());
            String email = claims.getSubject();
            UserEntity entity = getUserById(id);
            if (tokenId != id || !email.equals(entity.getEmail())) {
                throw new ResponseException(ResponseValue.WRONG_CLIENT_ID_OR_SECRET);
            }
            return entity;
        } catch (NumberFormatException | ClaimJwtException e) {
            throw new ResponseException(ResponseValue.INVALID_TOKEN);
        }
    }

    @Override
    public UserEntity insertUser(UserRegisterDto userRegisterDto) throws ResponseException {
        if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            throw new ResponseException(ResponseValue.EMAIL_HAS_BEEN_USED);
        }
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        UserEntity entity = userMapper.toEntity(userRegisterDto);
        return userRepository.save(entity);
    }

    @Override
    public UserEntity updateUser(String accessToken, Long id, UserUpdateInfoDto userUpdateInfoDto) throws ResponseException {
        UserEntity entity = verifyToken(accessToken, id);
        userMapper.partialUpdate(userUpdateInfoDto, entity);
        return userRepository.save(entity);
    }

    @Override
    public UserEntity updateUser(String accessToken, Long id, UserUpdatePasswordDto userUpdatePasswordDto) throws ResponseException {
        UserEntity entity = verifyToken(accessToken, id);
        userMapper.partialUpdate(userUpdatePasswordDto, entity);
        return userRepository.save(entity);
    }

    @Override
    public void deleteUserById(Long id) throws ResponseException {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUserByIds(Iterable<Long> ids) throws ResponseException {
        userRepository.deleteAllById(ids);
    }

    @Override
    public Iterable<UserEntity> getAllUser() throws ResponseException {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) throws ResponseException {
        return userRepository.findById(id).orElseThrow(() -> new ResponseException(ResponseValue.USER_NOT_FOUND));
    }

    @Override
    public UserEntity getUserByEmail(String email) throws ResponseException {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResponseException(ResponseValue.USER_NOT_FOUND));
    }
}
