package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.product.CreateProductPayload;
import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.dto.product.UpdateProductPayload;

import java.util.List;

public interface IProductService {
    ProductResponse getProduct(Long id);
    ProductResponse createProduct(CreateProductPayload createProductPayload);
    ProductResponse updateProduct(UpdateProductPayload updateProductPayload, Long id);
    void deleteProduct(Long id);
    List<ProductResponse> getAllProducts();
}
