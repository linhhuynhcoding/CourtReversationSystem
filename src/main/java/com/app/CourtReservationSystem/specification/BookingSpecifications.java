package com.app.CourtReservationSystem.specification;

import com.app.CourtReservationSystem.dto.booking.BookingFilter;
import com.app.CourtReservationSystem.model.Booking;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingSpecifications {
    public static Specification<Booking> withFilter(BookingFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // filter: DURATION
            LocalDateTime today = LocalDateTime.now().withHour(17).withMinute(0).withSecond(0);
            LocalDateTime fromDate = today;

            // TODO: fix time
            if (filter.getDuration() < 0) {
                fromDate = today.minusDays(1).withHour(17).withMinute(0).withSecond(0);
                today = today.plusDays(-filter.getDuration()).withHour(17);
            }
            else {
                fromDate = fromDate.minusDays(filter.getDuration());
            }

            System.out.println("today: " + today.toString());
            System.out.println("from: " + fromDate.toString());
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("timeStart"), fromDate));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("timeEnd"), today));

            if (filter.getBookingId() != null) {
                System.out.println(root.get("id"));

                predicates.add(criteriaBuilder.equal(root.get("id"), filter.getBookingId()));
            }

            if (filter.getOrgaId() != null) {
                System.out.println(root.get("orga").get("id"));
                predicates.add(criteriaBuilder.equal(root.get("orga").get("id"), filter.getOrgaId()));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
