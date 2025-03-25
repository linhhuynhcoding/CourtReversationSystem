package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.dto.product.UpdateProductPayload;
import com.app.CourtReservationSystem.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query("""
            UPDATE Product p SET 
                p.name = CASE WHEN :#{#np.name} IS NOT NULL THEN :#{#np.name} ELSE p.name END,   
                p.price = CASE WHEN :#{#np.price} IS NOT NULL THEN :#{#np.price} ELSE p.price END,   
                p.buyTurn = CASE WHEN :#{#np.buyTurn} IS NOT NULL THEN :#{#np.buyTurn} ELSE p.buyTurn END,   
                p.stock = CASE WHEN :#{#np.stock} IS NOT NULL THEN :#{#np.stock} ELSE p.stock END   
            WHERE p.id = :id
            """
    )
    int updateProductById(@Param("np") UpdateProductPayload payload, @Param("id") Long id);
}
