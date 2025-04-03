package com.app.CourtReservationSystem.dto.cart;

import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.model.Cart;
import com.app.CourtReservationSystem.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponse {
    private Long id;
    private ProductResponse product;
    private Long quantity;
    private boolean selected;
}
