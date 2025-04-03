package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.cart.CartResponse;
import com.app.CourtReservationSystem.dto.cart.UpdateItemPayload;
import com.app.CourtReservationSystem.security.CustomUserDetails;
import com.app.CourtReservationSystem.service.ICartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
        name = "cart"
)
public class CartController {
    ICartService cartService;

    @Operation(
            summary = "Get Cart REST API",
            description = "Get Cart REST API is used to get cart from database"
    )
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<ApiResponse<?>> getAccount(
            HttpServletRequest request,
            @PathVariable(name = "id") Long id
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getPrincipal());
        Long accountId = ((CustomUserDetails) auth.getPrincipal()).getId();

        CartResponse response = cartService.getCart(id, accountId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), response));
    }

    @Operation(
            summary = "Create or Update Cart Item REST API",
            description = "This API is used to add or update an item in the cart."
    )
    @PostMapping("/{id}")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<ApiResponse<?>> createOrUpdateCartItem(
            HttpServletRequest request,
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateItemPayload itemPayload
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long accountId = ((CustomUserDetails) auth.getPrincipal()).getId();

        CartResponse response = cartService.createOrUpdateItem(id, accountId, itemPayload);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Cart item updated successfully!", "",
                request.getRequestURI(), response));
    }

    @Operation(
            summary = "Delete Cart Item REST API",
            description = "This API is used to remove an item from the cart."
    )
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<ApiResponse<?>> deleteCartItem(
            HttpServletRequest request,
            @PathVariable(name = "id") Long cartId,
            @RequestParam(name = "itemId") Long itemId
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long accountId = ((CustomUserDetails) auth.getPrincipal()).getId();

        cartService.deleteItem(cartId, accountId, itemId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Cart item deleted successfully!", "",
                request.getRequestURI(), null));
    }
}
