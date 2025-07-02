package org.example.construction.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    @Email
    @NotBlank(message = "username cannot be null")
    String username;

    @NotBlank(message = "password cannot be null")
    @Size(min = 8,message = "password must be at least 8 characters long")
    String password;
}
