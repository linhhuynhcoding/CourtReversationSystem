package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.address.CreateAddressPayload;
import com.app.CourtReservationSystem.dto.court.OrgaResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import com.app.CourtReservationSystem.dto.image.ImageResponse;
import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.enums.ImageStatus;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.AddressMapper;
import com.app.CourtReservationSystem.mapper.CourtMapper;
import com.app.CourtReservationSystem.mapper.ImageMapper;
import com.app.CourtReservationSystem.model.Address;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.model.Organisation;
import com.app.CourtReservationSystem.model.Image;
import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import com.app.CourtReservationSystem.repository.OrgaRepository;
import com.app.CourtReservationSystem.repository.ImageRepository;
import com.app.CourtReservationSystem.service.ICourtService;
import com.app.CourtReservationSystem.service.IStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourtService implements ICourtService {
    OrgaRepository courtRepository;
    ImageRepository imageRepository;
    CourtMapper courtMapper;
    ImageMapper imageMapper;
    AddressMapper addressMapper;
    IStorageService fileStorageService;
    
    static Long SEVEN_DAYS_TIMESTAMP = 1000 * 3600 * 24 * 7L;
    
    @Override
    public OrgaResponse getCourt(Long id) {
        Organisation court = courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id", id));
        
        return courtMapper.toDTO(court);
    }
    
    @Override
    public OrgaResponse getCourt(Long id, Date startFrom) {
        Date endAt = new Date(startFrom.getTime() + SEVEN_DAYS_TIMESTAMP);
        Organisation court = courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id", id));
        
        return courtMapper.toDTO(court);
    }
    
    
    @Override
    @Transactional
    public OrgaResponse updateCourt(Long id, UpdateCourtPayload updateCourtPayload) {
        courtRepository.updateCourtById(id, updateCourtPayload);
        Organisation court = courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id", id));
        
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
    public OrgaResponse createCourt(CreateCourtPayload createCourtPayload) {
        Organisation orga = courtMapper.createToEntity(createCourtPayload);

        List<Court> courtList = new ArrayList<>();
        for (int j = 1; j <= orga.getNumberOfCourts(); j++) {
            Court court = new Court();
            court.setOrganisation(orga);
            court.setName(createCourtPayload.getCourtNames().get(j - 1));
            courtList.add(court);
        }
        orga.setCourts(courtList);
        
        // TODO Xu ly anh
        List<Image> images = this.imageMapper.toEntities(createCourtPayload.getImageCourts());
        List<ImageCourt> imageCourts = images.stream().map((image) -> {
            ImageCourt imageCourt = new ImageCourt();
            imageCourt.setImage(image);
            
            return imageCourt;
        }).collect(Collectors.toList());
        
        // TODO Xu ly address
        Address address = this.addressMapper.createToEntity(createCourtPayload.getAddress());
        
        orga.setImageCourts(imageCourts);
        orga.setAddress(address);
        orga.setStatus(CourtStatus.OPENING);
        
        courtRepository.save(orga);
        
        return courtMapper.toDTO(orga);
    }
    
    @Override
    public void deleteCourt(Long id) {
        courtRepository.deleteById(id);
    }
    
    @Override
    public Page getAllCourts(Pageable pageable) {

        Page<Organisation> courts = courtRepository.findAll(pageable);

        return courts.map(courtMapper::toDTO);
//        return courtMapper.toDTOs(courts);
    }
}
