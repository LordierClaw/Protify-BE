package com.proptit.protify_be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proptit.protify_be.entity.UserEntity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.proptit.protify_be.entity.UserEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto implements Serializable {
    @NotNull
    @NotEmpty
    String firstName;
    @NotNull
    @NotEmpty
    String lastName;
    @NotNull
    @Email
    @NotEmpty
    String email;
    @NotNull
    @NotEmpty
    String password;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    LocalDate dateOfBirth;
    @NotNull
    UserEntity.Gender gender;
    @Digits(integer = 10, fraction = 0)
    String phoneNumber;
}