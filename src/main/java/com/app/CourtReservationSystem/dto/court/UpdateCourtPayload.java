package com.app.CourtReservationSystem.dto.court;

import com.app.CourtReservationSystem.dto.address.CreateAddressPayload;
import com.app.CourtReservationSystem.dto.image.ImagePayload;
import com.app.CourtReservationSystem.dto.image.ImageResponse;
import com.app.CourtReservationSystem.enums.CourtStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class UpdateCourtPayload {
    private String name;
    
    @Pattern(regexp="^[0-9]{10,11}$")
    private String phone;

    private Long numberOfCourts;

    @Min(1000)
    private Double price;
    
    private CourtStatus status;
    
    @Valid
    private CreateAddressPayload address;
    
    @Size(min=3, max=5)
    private List<ImagePayload> imageCourts;
    
    @Size(min=0, max=5)
    private List<ImageResponse> oldImages;
}
