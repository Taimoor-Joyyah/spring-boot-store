package com.codewithmosh.store.auth;

import com.codewithmosh.store.users.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * DTO for {@link User}
 */
@Data
public class LoginRequest {
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is blank")
    private String email;
    @NotEmpty(message = "Password is empty")
    private String password;
}
