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
            LocalDateTime today = LocalDateTime.now().withHour(23).withMinute(59);
            LocalDateTime fromDate = today.minusDays(filter.getDuration());
            if (filter.getDuration() < 0) {
                fromDate = today.minusDays(1);
                today = today.plusDays(-filter.getDuration());
            }
            System.out.println("today: " + today.toString());
            System.out.println("from: " + fromDate.toString());
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("timeStart"), fromDate));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("timeStart"), today));

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
