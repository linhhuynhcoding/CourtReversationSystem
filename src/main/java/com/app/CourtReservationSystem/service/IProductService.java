package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.product.CreateProductPayload;
import com.app.CourtReservationSystem.dto.product.ProductFilter;
import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.dto.product.UpdateProductPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    ProductResponse getProduct(Long id);
    ProductResponse createProduct(CreateProductPayload createProductPayload);
    ProductResponse updateProduct(UpdateProductPayload updateProductPayload, Long id);
    void deleteProduct(Long id);
    Page getAllProducts(Pageable pageable, ProductFilter filter);
}
