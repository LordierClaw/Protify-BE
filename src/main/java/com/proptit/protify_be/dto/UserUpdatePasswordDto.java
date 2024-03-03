package com.proptit.protify_be.dto;

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
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatePasswordDto implements Serializable {
    @NotNull
    @NotEmpty
    Long id;
    @NotNull
    @NotEmpty
    String oldPassword;
    @NotNull
    @NotEmpty
    String newPassword;
}