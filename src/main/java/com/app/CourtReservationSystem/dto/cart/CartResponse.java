package com.app.CourtReservationSystem.dto.cart;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.model.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartResponse {
    private int id;
    private AccountResponse account;
    private List<CartResponse> items;
}
