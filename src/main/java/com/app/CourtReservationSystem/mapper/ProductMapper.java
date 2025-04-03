package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.product.CreateProductPayload;
import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(ignore = true, target = "category")
    Product toEntity(CreateProductPayload payload);

//    @Mapping(target = "category", expression = "java(product.getCategory().getName())")
    @Mapping(source = "imageProduct", target = "image")
    ProductResponse toDTO(Product product);

    List<ProductResponse> toDTOs(List<Product> products);

}
