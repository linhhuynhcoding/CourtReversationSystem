package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.booking.BookingResponse;
import com.app.CourtReservationSystem.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(source = "orga.id", target = "orgaId")
    @Mapping(source = "orga.name", target = "orgaName")
    @Mapping(source = "court.id", target = "courtId")
    @Mapping(source = "court.name", target = "courtName")
    BookingResponse toDTO(Booking booking);
    
    List<BookingResponse> toDTOs(List<Booking> bookings);

}
