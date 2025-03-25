package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.image.ImagePayload;
import com.app.CourtReservationSystem.dto.image.ImageResponse;
import com.app.CourtReservationSystem.model.Image;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toEntity(ImagePayload payload);
    ImageResponse toDTO(Image image);
    List<ImageResponse> toDTOs(List<Image> images);

}
