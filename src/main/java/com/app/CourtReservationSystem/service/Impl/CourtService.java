package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.address.CreateAddressPayload;
import com.app.CourtReservationSystem.dto.court.CourtFilter;
import com.app.CourtReservationSystem.dto.court.OrgaResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import com.app.CourtReservationSystem.dto.image.ImageResponse;
import com.app.CourtReservationSystem.enums.CourtSortField;
import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.enums.ImageStatus;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.AddressMapper;
import com.app.CourtReservationSystem.mapper.CourtMapper;
import com.app.CourtReservationSystem.mapper.ImageMapper;
import com.app.CourtReservationSystem.model.*;
import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import com.app.CourtReservationSystem.model.relationships.ManagerAccount;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.repository.BookingRepository;
import com.app.CourtReservationSystem.repository.OrgaRepository;
import com.app.CourtReservationSystem.repository.ImageRepository;
import com.app.CourtReservationSystem.service.ICourtService;
import com.app.CourtReservationSystem.service.IStorageService;
import com.app.CourtReservationSystem.specification.CourtSpecifications;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.app.CourtReservationSystem.utils.StringUtil.toOrders;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourtService implements ICourtService {
//    static final Logger logger = LoggerFactory.getLogger(CourtService.class);
    
    OrgaRepository orgaRepository;
    ImageRepository imageRepository;
    CourtMapper courtMapper;
    ImageMapper imageMapper;
    AddressMapper addressMapper;
    IStorageService fileStorageService;
    BookingRepository bookingRepository;
    
    @Autowired
    RedisService redisService;
    
    static Long SEVEN_DAYS_TIMESTAMP = 1000 * 3600 * 24 * 7L;
    private final AccountRepository accountRepository;
    
    
    @Override
    public void updateStatus(Long id, CourtStatus status) {
        Organisation court = orgaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id"
            , id));
        
        court.setStatus(status);
        orgaRepository.save(court);
    }
    
    @Override
    public OrgaResponse getCourt(Long id) {
        Object data = redisService.getData("court:" + id);

        if (data != null) {
            return (OrgaResponse) data;
        }
        
        Organisation court = orgaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id"
            , id));
        
        var orgaDTO = courtMapper.toDTO(court);
        
        redisService.saveData("court:" + id, orgaDTO);
        
        return orgaDTO;
    }
    
    @Override
    public OrgaResponse getCourtByManagerId(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account",
            "id", id));
        ManagerAccount managerAccount = account.getManager();
        Organisation court =
            orgaRepository.findByManager(managerAccount).orElseThrow(() -> new ResourceNotFoundException("Court", "id"
                , id));
        
        return courtMapper.toDTO(court);
    }
    
    @Override
    public OrgaResponse getCourt(Long id, Date startFrom) {
        Date endAt = new Date(startFrom.getTime() + SEVEN_DAYS_TIMESTAMP);
        Organisation court = orgaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id"
            , id));
        
        return courtMapper.toDTO(court);
    }
    
    
    @Override
    @Transactional
    public OrgaResponse updateCourt(Long id, UpdateCourtPayload updateCourtPayload) {
        orgaRepository.updateCourtById(id, updateCourtPayload);
        Organisation court = orgaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id"
            , id));
        
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
        
        if (updateCourtPayload.getAddress() != null) {
            CreateAddressPayload createAddressPayload = updateCourtPayload.getAddress();
            court.getAddress().setCity(createAddressPayload.getCity());
            court.getAddress().setDistrict(createAddressPayload.getDistrict());
            court.getAddress().setWard(createAddressPayload.getWard());
            court.getAddress().setAddressLine(createAddressPayload.getAddressLine());
        }
        
        orgaRepository.save(court);
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
        
        List<Image> images = this.imageMapper.toEntities(createCourtPayload.getImageCourts());
        List<ImageCourt> imageCourts = images.stream().map((image) -> {
            ImageCourt imageCourt = new ImageCourt();
            imageCourt.setImage(image);
            imageCourt.setCourtImage(orga);
            
            return imageCourt;
        }).collect(Collectors.toList());
        
        Address address = this.addressMapper.createToEntity(createCourtPayload.getAddress());
        
        orga.setImageCourts(imageCourts);
        orga.setAddress(address);
        orga.setStatus(CourtStatus.OPENING);
        
        orgaRepository.save(orga);
        
        return courtMapper.toDTO(orga);
    }
    
    @Override
    public void deleteCourt(Long id) {
        orgaRepository.deleteById(id);
    }
    
    @Override
    public Page getAllCourts(Pageable pageable) {
        
        Page<Organisation> courts = orgaRepository.findAll(pageable);
        
        return courts.map(courtMapper::toDTO);
//        return courtMapper.toDTOs(courts);
    }
    
    @Override
    public Page getAllCourts(CourtFilter filter) {
        Specification<Organisation> spec = CourtSpecifications.withFilter(filter);
        
        List<Sort.Order> orders = toOrders(filter.getSort(), CourtSortField.class);
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by(orders));
        
        Page<Organisation> courts = orgaRepository.findAll(spec, pageable);
        
        return courts.map(courtMapper::toDTO);
    }
}
