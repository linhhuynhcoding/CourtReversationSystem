package com.app.CourtReservationSystem.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter @Getter
public class AccountUpdatePayload {
    @NotEmpty(message = "Should not empty!")
    @Email()
    private String email;

    @NotEmpty(message = "Should not empty!")
    @Length(min = 1)
    private String name;
}
