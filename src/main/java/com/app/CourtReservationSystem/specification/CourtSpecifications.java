package com.app.CourtReservationSystem.specification;

import com.app.CourtReservationSystem.dto.court.CourtFilter;
import com.app.CourtReservationSystem.dto.product.ProductFilter;
import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.model.Address;
import com.app.CourtReservationSystem.model.Category;
import com.app.CourtReservationSystem.model.Organisation;
import com.app.CourtReservationSystem.model.Product;
import com.app.CourtReservationSystem.model.relationships.CourtFull;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CourtSpecifications {
    public static Specification<Organisation> withFilter(CourtFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // filter: SEARCH
            if (filter.getSearch() != null && !filter.getSearch().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getSearch() + "%"));
            }

            // filter: STATUS
            if (filter.getDateTime() != null) {
                Join<Organisation, CourtFull> courtFullJoin = root.join("courtFull", JoinType.LEFT);
                LocalDateTime date = filter.getDateTime().withHour(0).withMinute(0).withSecond(0);
                date = date.plusDays(1);

                // Subquery để tìm Organisation có courtFull.date == filter date
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<CourtFull> subRoot = subquery.from(CourtFull.class);
                subquery.select(subRoot.get("organisation").get("id"))
                        .where(
                                criteriaBuilder.equal(subRoot.get("organisation").get("id"), root.get("id")),
                                criteriaBuilder.equal(subRoot.get("date"), date)
                        );

                // NOT EXISTS -> tức là orga này không có bất kỳ courtFull nào có date trùng
                predicates.add(criteriaBuilder.not(criteriaBuilder.exists(subquery)));
            }

            // filter: LOCATION
            if (filter.getLocation() != null ) {
                Join<Organisation, Address> addressJoin = root.join("address");
                predicates.add(criteriaBuilder.equal(addressJoin.get("city"), filter.getLocation()));
            }
            
            // filter: STATUS
            if (filter.getStatus() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
