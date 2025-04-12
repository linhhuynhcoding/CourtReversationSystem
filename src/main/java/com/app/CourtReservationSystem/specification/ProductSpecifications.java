package com.app.CourtReservationSystem.specification;

import com.app.CourtReservationSystem.dto.product.ProductFilter;
import com.app.CourtReservationSystem.model.Category;
import com.app.CourtReservationSystem.model.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductSpecifications {
    public static Specification<Product> withFilter(ProductFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Product, Category> categoryJoin = root.join("category");


            predicates.add(criteriaBuilder.equal(categoryJoin.get("name"), filter.getCategoryName()));
            predicates.add(criteriaBuilder.greaterThan(root.get("price"), filter.getPriceMin()));
            predicates.add(criteriaBuilder.lessThan(root.get("price"), filter.getPriceMax()));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
