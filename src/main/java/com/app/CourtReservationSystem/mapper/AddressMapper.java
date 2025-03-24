package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.address.AddressResponse;
import com.app.CourtReservationSystem.dto.address.CreateAddressPayload;
import com.app.CourtReservationSystem.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address createToEntity(CreateAddressPayload createAddressPayload);
    
    AddressResponse toDTO(Address address);
}