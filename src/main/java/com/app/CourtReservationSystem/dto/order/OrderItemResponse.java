package com.app.CourtReservationSystem.dto.order;

import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.model.Order;
import com.app.CourtReservationSystem.model.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemResponse {
    private Long id;
    private ProductResponse product;
    private Long quantity;
    private Double unitPrice;
    private Double totalPrice;
}
