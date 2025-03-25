package com.app.CourtReservationSystem.dto.product;

import com.app.CourtReservationSystem.dto.image.ImagePayload;
import com.app.CourtReservationSystem.dto.image.ImageResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateProductPayload {
    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Long buyTurn;
    @NotNull
    private Long stock;
    @NotNull
    private Long category_id;
    @NotNull
    private ImagePayload image;
}
