package com.app.CourtReservationSystem.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class AccountResponse {
    private String username;

    private String email;

    private String name;

    private Long cartId;
}
