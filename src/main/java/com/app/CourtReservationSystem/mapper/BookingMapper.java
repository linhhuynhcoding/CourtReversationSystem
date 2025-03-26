package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.booking.BookingResponse;
import com.app.CourtReservationSystem.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingResponse toDTO(Booking booking);
    
    List<BookingResponse> toDTOs(List<Booking> bookings);
    
}
