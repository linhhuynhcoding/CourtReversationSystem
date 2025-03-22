package com.app.CourtReservationSystem.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginPayload {
    @NotEmpty
    @NotEmpty(message = "Should not empty!")
    @Length(min = 8, max = 20, message = "Username's length must be between 8 and 20 characters!")
    private String username;
    
    @NotEmpty(message = "Should not empty!")
    @Length(min = 8, max = 20, message = "Username's length must be between 8 and 20 characters!")
    @Pattern(
        regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+$",
        message = "Must be contain one special character and uppercase character!")
    private String password;
}
