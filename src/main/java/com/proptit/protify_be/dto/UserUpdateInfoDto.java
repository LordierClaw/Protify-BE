package com.proptit.protify_be.dto;

import com.proptit.protify_be.entity.UserEntity;
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
public class UserUpdateInfoDto implements Serializable {
    @NotNull
    @NotEmpty
    String firstName;
    @NotNull
    @NotEmpty
    String lastName;
    @NotNull
    LocalDate dateOfBirth;
    @NotNull
    UserEntity.Gender gender;
    String phoneNumber;
    String profilePicture;
}