package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.image.ImageResponse;
import com.app.CourtReservationSystem.mapper.ImageMapper;
import com.app.CourtReservationSystem.service.ICloudService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CloudinaryService extends Cloudinary implements ICloudService {
    
    @Autowired
    ImageMapper imageMapper;
    
    public CloudinaryService(@Value("${spring.application.couldinary_url}") String CLOUDINARY_URL,
                             ImageMapper imageMapper) {
        super(CLOUDINARY_URL);
        this.imageMapper = imageMapper;
    }
    
    @Override
    public List<ImageResponse> upload(List<MultipartFile> files) {
        List<Map> datas = files.stream().map((file) -> {
            try {
                return this.uploader().upload(file.getBytes(), Map.of());
                
            } catch (IOException io) {
                return Collections.emptyMap();
            }
        }).collect(Collectors.toList());
        
        return this.imageMapper.mapToDTOs(datas);
    }
}
