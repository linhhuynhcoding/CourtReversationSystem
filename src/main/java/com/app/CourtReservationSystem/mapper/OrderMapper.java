package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.order.OrderResponse;
import com.app.CourtReservationSystem.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toDTO(Order order);
    List<OrderResponse> toDTOs(List<Order> orders);
}
