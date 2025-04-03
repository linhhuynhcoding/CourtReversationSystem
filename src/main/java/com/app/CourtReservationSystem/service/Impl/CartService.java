package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.cart.CartResponse;
import com.app.CourtReservationSystem.dto.cart.UpdateItemPayload;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.CartMapper;
import com.app.CourtReservationSystem.model.Cart;
import com.app.CourtReservationSystem.repository.CartRepository;
import com.app.CourtReservationSystem.service.ICartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService implements ICartService {
    
    CartRepository cartRepository;
    CartMapper cartMapper;
    
    @Override
    public CartResponse getCart(Long cartId) {
        Cart cart = this.cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));
        return cartMapper.toDTO(cart);
    }
    
    @Override
    public CartResponse createOrUpdateItem(Long cartId, UpdateItemPayload itemPayload) {
        return null;
    }
    
    @Override
    public void deleteItem(Long cartId, Long itemId) {
    
    }
}
