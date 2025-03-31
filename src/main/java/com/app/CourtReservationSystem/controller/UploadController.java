package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.service.ICloudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<?>> uploadImage(@Valid @NotNull @RequestPart(name = "images", required = false) List<MultipartFile> files){
        List<Map> data = this.cloudService.upload(files);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
