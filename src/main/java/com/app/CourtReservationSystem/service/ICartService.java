package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.cart.CartResponse;
import com.app.CourtReservationSystem.dto.cart.UpdateItemPayload;

public interface ICartService {
    
    CartResponse getCart(Long cartId);
    CartResponse createOrUpdateItem(Long cartId, UpdateItemPayload itemPayload);
    void deleteItem(Long cartId, Long itemId);
    
}
