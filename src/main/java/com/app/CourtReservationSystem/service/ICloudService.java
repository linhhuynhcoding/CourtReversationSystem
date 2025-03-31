package com.app.CourtReservationSystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.List;

public interface ICloudService {
    List<Map> upload(List<MultipartFile> files);
}
