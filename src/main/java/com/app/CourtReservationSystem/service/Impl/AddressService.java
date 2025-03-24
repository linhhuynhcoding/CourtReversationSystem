package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.mapper.AddressMapper;
import com.app.CourtReservationSystem.service.IAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressService implements IAddressService {
    AddressMapper addressMapper;
}
