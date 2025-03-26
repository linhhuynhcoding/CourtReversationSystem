package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.service.ICloudService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import com.cloudinary.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryService extends Cloudinary implements ICloudService {
    @Value("${spring.application.couldinary_url}")
    private static String CLOUDINARY_URL;
    
    public CloudinaryService(){
        super(CLOUDINARY_URL);
    }
}
