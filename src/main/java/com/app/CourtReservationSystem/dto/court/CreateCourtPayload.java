package com.app.CourtReservationSystem.dto.court;

import com.app.CourtReservationSystem.dto.address.AddressResponse;
import com.app.CourtReservationSystem.dto.address.CreateAddressPayload;
import com.app.CourtReservationSystem.dto.image.ImagePayload;
import com.app.CourtReservationSystem.dto.image.ImageResponse;
import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CreateCourtPayload {
    @NotNull
    private String name;
    
    @Pattern(regexp="^[0-9]{10,11}$")
    @NotNull
    private String phone;
    @NotNull
    private Long numberOfCourts;

    @NotNull
    private List<String> courtNames;

    @NotNull
    @Min(1000)
    private Double price;
    
    @Valid
    @NotNull
    private CreateAddressPayload address;

    @NotNull
    @Size(min=3, max=5)
    private List<ImagePayload> imageCourts;
}
