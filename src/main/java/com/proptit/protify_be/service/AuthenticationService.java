package com.proptit.protify_be.service;

import com.proptit.protify_be.dto.UserAccessTokenDto;
import com.proptit.protify_be.dto.UserAllTokenDto;
import com.proptit.protify_be.dto.UserLoginDto;
import com.proptit.protify_be.exception.ResponseException;

public interface AuthenticationService {
    UserAccessTokenDto getNewAccessToken(String refreshToken) throws ResponseException;
    UserAllTokenDto validateAndGetToken(UserLoginDto userLoginDto) throws ResponseException;
}
