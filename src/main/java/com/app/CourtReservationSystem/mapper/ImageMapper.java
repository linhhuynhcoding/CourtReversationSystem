package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.image.ImagePayload;
import com.app.CourtReservationSystem.dto.image.ImageResponse;
import com.app.CourtReservationSystem.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toEntity(ImagePayload payload);
    List<Image> toEntities(List<ImagePayload> payloads);
    ImageResponse toDTO(Image image);
    List<ImageResponse> toDTOs(List<Image> images);

    @Mapping(expression = "java((String) image.get(\"url\"))", target = "image_url")
    @Mapping(expression = "java((Integer) image.get(\"width\"))", target = "width")
    @Mapping(expression = "java((Integer) image.get(\"height\"))", target = "height")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "status", ignore = true)
    ImageResponse mapToDTO(Map<String, Object> image);
    
    List<ImageResponse> mapToDTOs(List<Map> images);
}
