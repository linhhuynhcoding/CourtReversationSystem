package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.image.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.List;

public interface ICloudService {
    List<ImageResponse> upload(List<MultipartFile> files);
}
