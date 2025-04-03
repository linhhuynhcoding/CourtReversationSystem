package com.app.CourtReservationSystem.dto.cart;

import com.app.CourtReservationSystem.dto.product.ProductResponse;

public class UpdateItemPayload {
    private Long productId;
    private Long quantity;
    private boolean selected;
}
