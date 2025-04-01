package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.address.CreateAddressPayload;
import com.app.CourtReservationSystem.dto.court.CourtResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import com.app.CourtReservationSystem.dto.image.ImagePayload;
import com.app.CourtReservationSystem.dto.image.ImageResponse;
import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.enums.ImageStatus;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.AddressMapper;
import com.app.CourtReservationSystem.mapper.CourtMapper;
import com.app.CourtReservationSystem.mapper.ImageMapper;
import com.app.CourtReservationSystem.model.Address;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.model.Image;
import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import com.app.CourtReservationSystem.repository.CourtRepository;
import com.app.CourtReservationSystem.repository.ImageRepository;
import com.app.CourtReservationSystem.service.ICourtService;
import com.app.CourtReservationSystem.service.IStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourtService implements ICourtService {
    CourtRepository courtRepository;
    ImageRepository imageRepository;
    CourtMapper courtMapper;
    ImageMapper imageMapper;
    AddressMapper addressMapper;
    IStorageService fileStorageService;
    
    static Long SEVEN_DAYS_TIMESTAMP = 1000 * 3600 * 24 * 7L;
    
    @Override
    public CourtResponse getCourt(Long id) {
        Court court = courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id", id));
        
        return courtMapper.toDTO(court);
    }
    
    @Override
    public CourtResponse getCourt(Long id, Date startFrom) {
        Date endAt = new Date(startFrom.getTime() + SEVEN_DAYS_TIMESTAMP);
        Court court = courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id", id));
        
        return courtMapper.toDTO(court);
    }
    
    
    @Override
    @Transactional
    public CourtResponse updateCourt(Long id, UpdateCourtPayload updateCourtPayload) {
        courtRepository.updateCourtById(id, updateCourtPayload);
        Court court = courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id", id));
        
        // TODO Xu ly anh
        List<ImageResponse> oldImages = updateCourtPayload.getOldImages();
        List<Image> images = this.imageMapper.toEntities(updateCourtPayload.getImageCourts());
        
        oldImages.stream().forEach((imageDTO) -> {
            Image i = imageRepository.findById(imageDTO.getId()).orElseThrow(null);
            if (imageDTO.getStatus() == ImageStatus.INACTIVE) {
                i.setStatus(ImageStatus.INACTIVE); // SET INACTIVE TO DELETE IN FUTURE BY CRON
                imageRepository.save(i);
            }
        });
        
        List<ImageCourt> newImages = images.stream().map((image) -> {
            ImageCourt imageCourt = new ImageCourt();
            imageCourt.setImage(image);
            
            return imageCourt;
        }).collect(Collectors.toList());
        
        if (!newImages.isEmpty()) court.setImageCourts(newImages);
        
        // TODO Xu ly dia chi
        if (updateCourtPayload.getAddress() != null) {
            CreateAddressPayload createAddressPayload = updateCourtPayload.getAddress();
            court.getAddress().setCity(createAddressPayload.getCity());
            court.getAddress().setDistrict(createAddressPayload.getDistrict());
            court.getAddress().setWard(createAddressPayload.getWard());
            court.getAddress().setAddressLine(createAddressPayload.getAddressLine());
        }
        
        courtRepository.save(court);
        return courtMapper.toDTO(court);
    }
    
    @Override
    @Transactional
    public CourtResponse createCourt(CreateCourtPayload createCourtPayload) {
        Court court = courtMapper.createToEntity(createCourtPayload);
        
        // TODO Xu ly anh
        List<Image> images = this.imageMapper.toEntities(createCourtPayload.getImageCourts());
        List<ImageCourt> imageCourts = images.stream().map((image) -> {
            ImageCourt imageCourt = new ImageCourt();
            imageCourt.setImage(image);
            
            return imageCourt;
        }).collect(Collectors.toList());
        
        // TODO Xu ly address
        Address address = this.addressMapper.createToEntity(createCourtPayload.getAddress());
        
        court.setImageCourts(imageCourts);
        court.setAddress(address);
        court.setStatus(CourtStatus.OPENING);
        
        courtRepository.save(court);
        
        return courtMapper.toDTO(court);
    }
    
    @Override
    public void deleteCourt(Long id) {
        courtRepository.deleteById(id);
    }
    
    @Override
    public List<CourtResponse> getAllCourts() {
        List<Court> courts = courtRepository.findAll();
        return courtMapper.toDTOs(courts);
    }
}
