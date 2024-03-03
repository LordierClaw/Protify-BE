package com.proptit.protify_be.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.proptit.protify_be.entity.UserEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicInfoDto implements Serializable {
    @NotNull
    @NotEmpty
    Long id;
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
    String profilePicture;
}