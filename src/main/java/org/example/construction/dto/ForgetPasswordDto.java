package org.example.construction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgetPasswordDto {
    @NotBlank(message = "password field cannot be empty")
    @Size(min = 8)
    String newPassword;
    @NotBlank(message = "password field cannot be empty")
    @Size(min = 8)
    String confirmPassword;
}
