package com.app.CourtReservationSystem.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Normalized;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPayload {
    @NotEmpty(message = "Should not empty!")
    @Length(min = 8, max = 20, message = "Username's length must be between 8 and 20 characters!")
    protected String username;
    
    @NotEmpty(message = "Should not empty!")
    @Email()
    protected String email;
    
    @NotEmpty(message = "Should not empty!")
    @Length(min = 1)
    protected String name;
    
    @NotEmpty(message = "Should not empty!")
    @Length(min = 8, message = "Password's length must be equal or larger than 8 characters!")
    @Pattern(
        regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+$",
        message = "Must be contain one special character and uppercase character!")
    protected String password;
}

