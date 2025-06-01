package org.example.construction.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    @Email
    @NotBlank(message = "username cannot be null")
    String username;

    @NotBlank(message = "password cannot be null")
    @Min(value = 8)
    String password;
}
