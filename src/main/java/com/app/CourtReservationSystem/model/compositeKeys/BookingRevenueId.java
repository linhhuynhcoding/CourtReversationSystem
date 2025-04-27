package com.app.CourtReservationSystem.model.compositeKeys;

import com.app.CourtReservationSystem.model.Organisation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BookingRevenueId {
    private Organisation organisation;

    private LocalDateTime date;

    public BookingRevenueId() {}

    public BookingRevenueId(Organisation organisation, LocalDateTime date) {
        this.organisation = organisation;
        this.date = date;
    }
}
