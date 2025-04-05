package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.order.OrderResponse;
import com.app.CourtReservationSystem.dto.order.PlaceOrderBookingPayload;
import com.app.CourtReservationSystem.dto.order.PlaceOrderPayload;
import com.app.CourtReservationSystem.dto.payment.PaymentPayload;
import com.app.CourtReservationSystem.enums.OrderType;
import com.app.CourtReservationSystem.exception.APIException;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.AddressMapper;
import com.app.CourtReservationSystem.mapper.OrderMapper;
import com.app.CourtReservationSystem.model.*;
import com.app.CourtReservationSystem.repository.*;
import com.app.CourtReservationSystem.service.IOrderService;
import com.app.CourtReservationSystem.service.IPaymentService;
import com.cloudinary.api.exceptions.ApiException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {
    IPaymentService paymentService;
    AccountRepository accountRepository;
    OrderRepository orderRepository;
    BookingRepository bookingRepository;
    ProductRepository productRepository;
    CartRepository cartRepository;
    CartItemRepository itemRepository;
    CartItemRepository cartItemRepository;
    OrderMapper orderMapper;
    AddressMapper addressMapper;

    @Override
    @Transactional
    public OrderResponse placeOrder(Long accountId, PlaceOrderPayload payload) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));
        Address address = addressMapper.createToEntity(payload.getCreateAddressPayload());
        Cart cart = cartRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Cart", "accountId", accountId));
        List<CartItem> cartItems = cart.getItems();
        if (cartItems.isEmpty()) {
            throw new APIException("No cart item is selected!", HttpStatus.BAD_REQUEST);
        }
        Order order = new Order();
        List<OrderItem> orderItems = cartItems.stream().map((item) -> {
            OrderItem orderItem = new OrderItem();
            if (item.getProduct().getStock() < item.getQuantity()) {
                throw new APIException("Vượt quá số lượng tồn kho!!", HttpStatus.BAD_REQUEST);
            }
            orderItem.setQuantity(item.getQuantity());
            orderItem.setProduct(item.getProduct());
            orderItem.setUnitPrice(item.getProduct().getPrice());
            orderItem.setTotalPrice(item.getProduct().getPrice() * item.getQuantity());

            cartItemRepository.deleteById(item.getId());
            return orderItem;
        }).toList();

        Double totalPrice = orderItems.stream().mapToDouble(OrderItem::getTotalPrice).sum();
        order.setOrderItems(orderItems);
        order.setTotal(totalPrice);
        order.setOrderType(OrderType.DELIVERY);
        order.setAccount(account);
        order.setAddress(address);
        order.setShipFee(50000.0);

        PaymentPayload paymentPayload = new PaymentPayload();
        paymentPayload.setPaymentMethod(payload.getPaymentMethod());
        paymentPayload.setAmount(totalPrice);
        Payment payment = paymentService.createPayment(paymentPayload);

        order.setPayment(payment);
        orderRepository.save(order);

        Double totalCartPrice = cart.getItems().stream().mapToDouble(CartItem::getPrice).sum();
        cart.setTotalPrice(totalCartPrice);
        cartRepository.save(cart);

        return orderMapper.toDTO(order);
    }

    @Override
    public OrderResponse placeOrderBooking(Long accountId, Long bookingId, PlaceOrderBookingPayload payload) {
        Booking bookingProxy = bookingRepository.getReferenceById(bookingId);
        Account account = accountRepository.getReferenceById(accountId);

        try {
            bookingProxy.getId();
            account.getId();
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Booking", "id", bookingId);
        }

        List<OrderItem> orderItems = payload.getItems().stream().map((item) -> {
            OrderItem orderItem = new OrderItem();

            try {
                Product productProxy = productRepository.getReferenceById(item.getProductId());
                if (productProxy.getStock() < item.getQuantity()) {
                    throw new APIException("Vượt quá số lượng tồn kho!!", HttpStatus.BAD_REQUEST);
                }
                productRepository.decreamentStock(productProxy.getId(), item.getQuantity());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setProduct(productProxy);
                orderItem.setUnitPrice(productProxy.getPrice());
                orderItem.setTotalPrice(productProxy.getPrice() * item.getQuantity());
                return orderItem;
            }
            catch (EntityNotFoundException e) {
                throw new ResourceNotFoundException("Product", "id", item.getProductId());
            }
        }).toList();

        Double totalPrice = orderItems.stream().mapToDouble(OrderItem::getTotalPrice).sum();

        Order order = new Order();
        order.setOrderItems(orderItems);
        order.setTotal(totalPrice);
        order.setOrderType(OrderType.PICKUP_AT_COURT);
        order.setBooking(bookingProxy);
        order.setAccount(account);

        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    @Override
    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return orderMapper.toDTO(order);
    }

    @Override
    public Page getAccountOrders(Long id, Pageable pageable) {
        Page orders = orderRepository.findAllByAccountId(id, pageable);

        return orders;
    }

    @Override
    public Page getOrders(Pageable pageable) {

        return orderRepository.findAll(pageable);
    }


}
