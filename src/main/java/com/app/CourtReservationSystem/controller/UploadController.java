package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.service.ICloudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/uploads")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
    name = "upload"
)
public class UploadController {
    
    ICloudService cloudService;
    
    @PostMapping()
    public ResponseEntity<?> uploadImage(@RequestPart(name = "images", required = true) List<MultipartFile> images){
        if (images == null || images.isEmpty()) {
            return ResponseEntity.badRequest().body("No images uploaded.");
        }

        List<?> data = this.cloudService.upload(images);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
