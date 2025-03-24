package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.vietnameProvince.DistrictResponse;
import com.app.CourtReservationSystem.dto.vietnameProvince.ProvinceResponse;
import com.app.CourtReservationSystem.dto.vietnameProvince.WardResponse;
import com.app.CourtReservationSystem.service.IVietnamProviceSerivce;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @apiNote : https://provinces.open-api.vn/
 * In this service, I call the third-party api from https://provinces.open-api.vn/
 * Thanks full for your contribution
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VietnamProvinceService implements IVietnamProviceSerivce {
    private static final String HOST = "https://provinces.open-api.vn/api";
    private static final String PROVINCE_ENDPOINT = "/p";
    private static final String DISTRICT_ENDPOINT = "/d";
    private static final String WARD_ENDPOINT = "/w";
    
    @Override
    public List<?> getAllCities() {
        RestTemplate restTemplate = new RestTemplate();
        ProvinceResponse[] provinceResponses = restTemplate.getForObject(HOST + PROVINCE_ENDPOINT,
            ProvinceResponse[].class);
        
        return new ArrayList<>(Arrays.asList(provinceResponses));
    }
    
    @Override
    public List<?> getAllDistricts(Long city_code) {
        RestTemplate restTemplate = new RestTemplate();
        
        ProvinceResponse<DistrictResponse<?>> provinceResponse = restTemplate.getForObject(HOST + PROVINCE_ENDPOINT + "/" + city_code + "?depth=2",
            ProvinceResponse.class);
        
        return new ArrayList<>(Arrays.asList(provinceResponse.getDistricts()));
    }
    
    @Override
    public List<?> getAllWards(Long district_code) {
        RestTemplate restTemplate = new RestTemplate();
        
        DistrictResponse<WardResponse> districtResponses = restTemplate.getForObject(HOST + DISTRICT_ENDPOINT + "/" + district_code + "?depth=2",
            DistrictResponse.class);
        
        return new ArrayList<>(Arrays.asList(districtResponses.getWards()));
    }
    
}
