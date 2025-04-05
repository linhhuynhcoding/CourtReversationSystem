package com.app.CourtReservationSystem.dto.cart;

import com.app.CourtReservationSystem.dto.product.ProductResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemPayload {
    private Long productId;
    private Integer quantity;
    private boolean selected;
}
