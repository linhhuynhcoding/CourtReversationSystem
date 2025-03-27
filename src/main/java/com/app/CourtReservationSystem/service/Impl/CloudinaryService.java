package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.service.ICloudService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import com.cloudinary.*;

@Service
@RequiredArgsConstructor
public class CloudinaryService extends Cloudinary implements ICloudService {
    public CloudinaryService(@Value("${spring.application.couldinary_url}") String CLOUDINARY_URL){
        super(CLOUDINARY_URL);
    }
}
