package com.app.CourtReservationSystem.specification;

import com.app.CourtReservationSystem.dto.booking.BookingFilter;
import com.app.CourtReservationSystem.dto.court.CourtFilter;
import com.app.CourtReservationSystem.model.Address;
import com.app.CourtReservationSystem.model.Booking;
import com.app.CourtReservationSystem.model.Organisation;
import com.app.CourtReservationSystem.model.relationships.CourtFull;
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
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), fromDate));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), today));
            
            if (filter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
            }
            
            if (filter.getOrgaId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("orga").get("id"), filter.getOrgaId()));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
