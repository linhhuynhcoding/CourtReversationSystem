package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.cart.CartItemResponse;
import com.app.CourtReservationSystem.dto.cart.CartResponse;
import com.app.CourtReservationSystem.model.Cart;
import com.app.CourtReservationSystem.model.CartItem;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    
    CartResponse toDTO(Cart cart);
    
    CartItemResponse toItemDTO(CartItem cartItem);
    List<CartItemResponse> toItemDTOs(List<CartItem> cartItems);
}
