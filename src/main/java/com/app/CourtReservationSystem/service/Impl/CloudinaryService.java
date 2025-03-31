package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.service.ICloudService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import com.cloudinary.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CloudinaryService extends Cloudinary implements ICloudService {
    public CloudinaryService(@Value("${spring.application.couldinary_url}") String CLOUDINARY_URL) {
        super(CLOUDINARY_URL);
    }
    
    @Override
    public List<Map> upload(List<MultipartFile> files) {
        return files.stream().map((file) -> {
            try {
                return this.uploader().upload(file.getBytes(), Map.of());
                
            } catch (IOException io) {
                return Collections.emptyMap();
            }
        }).collect(Collectors.toList());
    }
}
