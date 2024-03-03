package com.proptit.protify_be.service.impl;

import com.proptit.protify_be.constants.ResponseValue;
import com.proptit.protify_be.dto.UserAccessTokenDto;
import com.proptit.protify_be.dto.UserAllTokenDto;
import com.proptit.protify_be.dto.UserLoginDto;
import com.proptit.protify_be.entity.UserEntity;
import com.proptit.protify_be.exception.ResponseException;
import com.proptit.protify_be.security.AuthManager;
import com.proptit.protify_be.service.AuthenticationService;
import com.proptit.protify_be.service.UserService;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthManager authManager;

    public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder, AuthManager authManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    @Override
    public UserAccessTokenDto getNewAccessToken(String refreshToken) throws ResponseException {
        Claims claims = authManager.decodeToken(refreshToken);
        if (claims.getIssuedAt().compareTo(new Date()) > 0) {
            throw new ResponseException(ResponseValue.EXPIRED_TOKEN);
        }
        try {
            long id = Long.parseLong(claims.getId());
            String username = claims.getSubject();
            return new UserAccessTokenDto(authManager.generateAccessToken(id, username), authManager.getAccessTokenValidity());
        } catch (NumberFormatException | ClaimJwtException e) {
            throw new ResponseException(ResponseValue.INVALID_TOKEN);
        }
    }

    @Override
    public UserAllTokenDto validateAndGetToken(UserLoginDto userLoginDto) throws ResponseException {
        UserEntity entity = userService.getUserByEmail(userLoginDto.getEmail());
        if (!passwordEncoder.matches(userLoginDto.getPassword(), entity.getPassword())) {
            throw new ResponseException(ResponseValue.WRONG_PASSWORD);
        }
        return new UserAllTokenDto(
                authManager.generateAccessToken(entity.getId(), entity.getEmail()),
                authManager.getAccessTokenValidity(),
                authManager.generateRefreshToken(entity.getId(), entity.getEmail()),
                authManager.getRefreshTokenValidity()
        );
    }
}
