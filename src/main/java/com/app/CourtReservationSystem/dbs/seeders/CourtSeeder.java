package com.app.CourtReservationSystem.dbs.seeders;

import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.enums.ResolutionType;
import com.app.CourtReservationSystem.model.Address;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.model.Image;
import com.app.CourtReservationSystem.model.Organisation;
import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import com.app.CourtReservationSystem.repository.OrgaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Order(3)
public class CourtSeeder implements CommandLineRunner {


    @Autowired
    OrgaRepository courtRepository;

    private static final String[] cities = {"City A", "City B", "City C", "City D"};
    private static final String[] districts = {"District A", "District B", "District C", "District D"};
    private static final String[] wards = {"Ward A", "Ward B", "Ward C", "Ward D"};
    private static final String[] image_urls = {
        "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151475/j8i2mjbzq1k0qknxgusu.webp",
        "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151476/nr6ixoht5bjkwat0esdm.webp",
        "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151477/ukavhj5fhshlbi1tpxd7.webp"
};

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CourtSeeder starting...");
        loadCourtData();
    }

    private void loadCourtData() {
        List<Organisation> courts = new ArrayList<>() {
        };

        for (int i = 0; i <= 100; i++) {
            Organisation orga = new Organisation();
            Address address = new Address();

            orga.setName("Orga  " + i);
            orga.setPrice((Math.floor(Math.random() * 100000) % 1000 + 150) * 1000);
            orga.setNumberOfCourts(Math.round(Math.random() * 100000 % 1) + 1);

            List<Court> courtList = new ArrayList<>();
            for (int j = 1; j <= orga.getNumberOfCourts(); j++) {
                Court court = new Court();
                court.setOrganisation(orga);
                court.setName("Court " + (char) ('A' + j - 1));
                courtList.add(court);
            }
            orga.setCourts(courtList);
            orga.setPhone("+84 999 129 11" + i);

            address.setAddressLine("" + i);
            address.setCity(cities[i % 4]);
            address.setWard(wards[i % 4]);
            address.setDistrict(districts[i % 4]);
            address.setLatitude(Math.round(Math.random() * 10000000));
            address.setLongitude(Math.round(Math.random() * 10000000));

            orga.setAddress(address);
            orga.setStatus(CourtStatus.values()[i % 3]);

            List<ImageCourt> images = Arrays.stream(image_urls).map((img) -> {
                ImageCourt imageCourt = new ImageCourt();
                Image image = new Image();
                image.setImage_url(img);
                image.setHeight(800);
                image.setWidth(800);
                image.setType(ResolutionType.ORIGINAL);

                imageCourt.setImage(image);
                imageCourt.setCourtImage(orga);
                return imageCourt;
            }).toList();
            orga.setImageCourts(images);

            courts.add(orga);
        }

        courtRepository.saveAll(courts);
    }
}
