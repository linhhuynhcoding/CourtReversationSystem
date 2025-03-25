package com.app.CourtReservationSystem.dto.product;

import com.app.CourtReservationSystem.dto.image.ImageResponse;
import com.app.CourtReservationSystem.model.CartItem;
import com.app.CourtReservationSystem.model.Category;
import com.app.CourtReservationSystem.model.Image;
import com.app.CourtReservationSystem.repository.CategoryRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private Long buyTurn;
    private Long stock;
    private String category;
    private ImageResponse image;
}
