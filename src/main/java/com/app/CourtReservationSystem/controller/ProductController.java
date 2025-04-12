package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.product.CreateProductPayload;
import com.app.CourtReservationSystem.dto.product.ProductFilter;
import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.dto.product.UpdateProductPayload;
import com.app.CourtReservationSystem.enums.CourtSortField;
import com.app.CourtReservationSystem.enums.ProductSortField;
import com.app.CourtReservationSystem.exception.APIException;
import com.app.CourtReservationSystem.model.Product;
import com.app.CourtReservationSystem.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.CourtReservationSystem.utils.StringUtil.toOrders;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
        name = "product"
)
public class ProductController {
    IProductService productService;

    @Operation(
            summary = "Get Product REST API"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getProduct(
            HttpServletRequest request,
            @PathVariable("id") Long id) {
        ProductResponse response = productService.getProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }

    @Operation(summary = "Create Product REST API")
    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> createProduct(
            HttpServletRequest request,
            @RequestBody CreateProductPayload createProductPayload) {
        ProductResponse response = productService.createProduct(createProductPayload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Product created", "", request.getRequestURI(), response));
    }

    @Operation(summary = "Update Product REST API")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateProduct(
            HttpServletRequest request,
            @PathVariable("id") Long id,
            @RequestBody UpdateProductPayload payload) {
        ProductResponse response = productService.updateProduct(payload, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Product updated", "", request.getRequestURI(), response));
    }

    @Operation(summary = "Delete Product REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteProduct(
            HttpServletRequest request,
            @PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>("Product deleted", "", request.getRequestURI(), null));
    }

    @Operation(summary = "Get All Products REST API")
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllProducts(HttpServletRequest request,@Valid ProductFilter filter) {
        List<Sort.Order> orders = toOrders(filter.getSort(), ProductSortField.class);
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by(orders));

        Page response = productService.getAllProducts(pageable, filter);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }

}
