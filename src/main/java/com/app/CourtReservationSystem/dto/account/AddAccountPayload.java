package com.app.CourtReservationSystem.dto.account;

import com.app.CourtReservationSystem.dto.auth.RegisterPayload;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AddAccountPayload extends RegisterPayload {
    @NotEmpty
    private String role;

    private Long orgaId;
}
