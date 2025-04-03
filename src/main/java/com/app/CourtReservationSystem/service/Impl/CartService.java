package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.cart.CartResponse;
import com.app.CourtReservationSystem.dto.cart.UpdateItemPayload;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.CartMapper;
import com.app.CourtReservationSystem.model.Cart;
import com.app.CourtReservationSystem.model.CartItem;
import com.app.CourtReservationSystem.model.Product;
import com.app.CourtReservationSystem.repository.CartItemRepository;
import com.app.CourtReservationSystem.repository.CartRepository;
import com.app.CourtReservationSystem.repository.ProductRepository;
import com.app.CourtReservationSystem.service.ICartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService implements ICartService {
    
    CartRepository cartRepository;
    ProductRepository productRepository;
    CartItemRepository cartItemRepository;
    CartMapper cartMapper;
    
    @Override
    public CartResponse getCart(Long cartId, Long accountId) {
        Cart cart = this.cartRepository.findByIdAndAccountId(cartId, accountId).orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));
        return cartMapper.toDTO(cart);
    }
    
    @Override
    public CartResponse createOrUpdateItem(Long cartId, Long accountId, UpdateItemPayload itemPayload) {
        Cart cart = this.cartRepository.findByIdAndAccountId(cartId, accountId).orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        Optional<CartItem> item = this.cartItemRepository.findByCartIdAndProductId(cartId, itemPayload.getProductId());
        if (item.isEmpty()) {
            CartItem cartItem = new CartItem();
            Product product = this.productRepository.findById(itemPayload.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", itemPayload.getProductId()));
            cartItem.setProduct(product);
            cartItem.setQuantity(itemPayload.getQuantity());
            cartItem.setCart(cart);
            this.cartItemRepository.save(cartItem);
            cart.getItems().add(cartItem);
        }
        else {
            item.get().setQuantity(itemPayload.getQuantity());
            item.get().setSelected(itemPayload.isSelected());
        }

        cartRepository.save(cart);
        return cartMapper.toDTO(cart);
    }
    
    @Override
    public void deleteItem(Long cartId, Long accountId, Long itemId) {
        Cart cart = this.cartRepository.findByIdAndAccountId(cartId, accountId).orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));
        CartItem item = this.cartItemRepository.findByIdAndCartId(itemId, cartId).orElseThrow(() -> new ResourceNotFoundException("CartItem", "itemId", itemId));

        this.cartItemRepository.delete(item);
    }
}
