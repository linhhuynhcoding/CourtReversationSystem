package com.app.CourtReservationSystem.dbs.seeders;

import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.model.Address;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.repository.CourtRepository;
import com.app.CourtReservationSystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(3)
public class CourtSeeder implements CommandLineRunner {
    
    
    @Autowired
    CourtRepository courtRepository;
    
    private static final String[] cities = {"City A", "City B", "City C" ,"City D"};
    private static final String[] districts = {"District A", "District B", "District C" ,"District D"};
    private static final String[] wards = {"Ward A", "Ward B", "Ward C" ,"Ward D"};
    
    @Override
    public void run(String... args) throws Exception {
//        loadCourtData();
    }
    
    private void loadCourtData(){
        List<Court> courts = new ArrayList<>() {};
        
        for (int i = 1; i <= 100; i++) {
            Court court = new Court();
            Address address = new Address();
            
            court.setName("Court " + i);
            court.setPrice(Math.floor(Math.random() * 100000));
            court.setNumberOfCourts(Math.round(Math.random() * 100000 % 10));
            court.setPhone("+84 999 129 11" + i);
            
            address.setAddressLine("" + i);
            address.setCity(cities[i % 4]);
            address.setWard(wards[i % 4]);
            address.setDistrict(districts[i % 4]);
            address.setLatitude(Math.round(Math.random() * 10000000));
            address.setLongitude(Math.round(Math.random() * 10000000));
            
            court.setAddress(address);
            court.setStatus(CourtStatus.values()[i % 3]);
            
            courts.add(court);
        }
        
        courtRepository.saveAll(courts);
    }
}
