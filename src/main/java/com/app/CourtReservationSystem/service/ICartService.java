package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.cart.CartResponse;
import com.app.CourtReservationSystem.dto.cart.UpdateItemPayload;

public interface ICartService {
    
    CartResponse getCart(Long cartId, Long accountId);
    CartResponse createOrUpdateItem(Long cartId, Long accountId, UpdateItemPayload itemPayload);
    void deleteItem(Long cartId, Long accountId, Long itemId);
    
}
