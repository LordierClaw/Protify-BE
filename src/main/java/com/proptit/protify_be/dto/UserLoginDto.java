package com.proptit.protify_be.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto implements Serializable {
    @NotNull
    @Email
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;
}