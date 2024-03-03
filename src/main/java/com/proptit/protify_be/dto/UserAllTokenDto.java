package com.proptit.protify_be.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAllTokenDto {
    @NotNull
    @NotEmpty
    private String accessToken;
    @NotNull
    @NotEmpty
    private long accessTokenValidity;
    @NotNull
    @NotEmpty
    private String refreshToken;
    @NotNull
    @NotEmpty
    private long refreshTokenValidity;
}
