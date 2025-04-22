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
    String names[] = {
            "Dưa Leo Đánh Cầu",
            "Pickle Power",
            "Sân Chấm Dưa",
            "Dưa Leo Trên Mạng",
            "Đánh Là Dính!",
            "Pickle Không Chua",
            "Cầu Dưa Tốc Độ",
            "Đánh Lên Dưa!",
            "Trùm Cây Vợt",
            "Pickle Lên Ngôi",
            "Leo Dưa Leo",
            "Đánh Là Dính",
            "Dưa Gặp Vợt",
            "Sân Dưa Đại Náo",
            "Không Chua, Chỉ Vui",
            "Cầu Bay Dưa Lượn",
            "Vợt Chạm Dưa",
            "Đập Là Vui",
            "Trùm Đánh Dưa",
            "Dưa Bay Như Gió",
            "Hội Đánh Dưa",
            "Đập Cho Đời Vui",
            "Lầy Trên Sân",
            "Sân Này Của Hội",
            "Team Cầu Mà Không Cần Cầu",
            "Sân Nhà Có Dưa",
            "Hội Gõ Cầu Thần Thánh",
            "Pickle Không Biết Mệt",
            "Đánh Cho Già Trẻ Đều Cười",
            "Lăn Xả Vì Cầu",
            "Dưa Cười Ra Nước Mắt",
            "Sân Cười Vỡ Bụng",
            "Cầu Cười Té Ghế",
            "Mỗi Cú Đập Một Tràng Cười",
            "Vợt Vỡ Vui Vẻ",
            "Đánh Là Rớt Não",
            "Không Cầu Mà Cũng Vui",
            "Đập Không Trượt Cái Dưa",
            "Đánh Cho Vui Chứ Không Cần Thắng",
            "Cười Xuyên Lưới",
            "Vợt Vui",
            "Dưa & Nắng",
            "Gió Lướt Dưa Bay",
            "Mỗi Ngày Một Vợt",
            "Nhẹ Nhàng Mà Đánh",
            "Dưa Dẻo Dai",
            "Sân Vui Vẻ",
            "Cầu Nhẹ Tênh",
            "Cầu Bay Bay",
            "Vợt Nhẹ Lòng Vui",
            "Dưa King",
            "Vợt Thánh",
            "Dưa Sư",
            "Đế Chế Pickle",
            "Chúa Tể Sân Cầu",
            "Trùm Đập Dưa",
            "Vô Địch Đường Phố",
            "Pickle Boss",
            "Ông Hoàng Pickleball",
            "Đánh Là Đỉnh",
            "Dưa Lý Trí",
            "Sân Sáng Dưa",
            "Trí Tuệ Pickle",
            "Cầu Vợt Logic",
            "Đánh Cầu - Không Cần Não?",
            "Lưới Không Có Tội",
            "Vợt & Não",
            "Dưa Hại Não",
            "Gõ Cầu Chiến Thuật",
            "Pickle Học Đường",
            "Đánh Cho Người Yêu Cũ Hối Hận",
            "Đập Là Hết Sầu",
            "Chơi Cho Bõ Ghét",
            "Đập Mặt Đối Thủ",
            "Đánh Xả Stress",
            "Team Gõ Bể Sân",
            "Không Đánh Không Về",
            "Đánh Là Hơi Thở",
            "Không Gõ Không Vui",
            "Đập Là Nghề, Cười Là Đam Mê",
            "Pickle Fest",
            "Đại Hội Đập Dưa",
            "Sân Chơi Cho Dưa Chín",
            "Vận Hội Pickle",
            "DưaBall Championship",
            "Siêu Cúp Pickle",
            "Giải Đánh Không Trượt",
            "Đập Dưa Tưng Bừng",
            "Pickle Funsion",
            "Giao Lưu Đập Gió",
            "Pickle Up",
            "VợtZui",
            "Dưa Go",
            "CầuBay",
            "SânMe",
            "Ping Dưa",
            "FunKle",
            "ĐậpZui",
            "PicklePong",
            "ZuiBall"
    };

    @Autowired
    OrgaRepository courtRepository;

    private static final String[] cities = {"Thành phố Hà Nội", "Thành phố Hồ Chí Minh", "Thành phố Đà Nẵng", "Thành phố Hải Phòng"};
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

            orga.setName(names[i % 50]);
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

            List<ImageCourt> images = Arrays.stream(imageGroups[i % 10]).map((img) -> {
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

    String[][] imageGroups = {
            {
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151472/oqmb43wxmmc9tz1zyiyv.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151473/rxb9dygix9mmz8qscjly.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151474/hvxrclpcdxqccl6lhtl0.webp"
            },
            {
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151475/j8i2mjbzq1k0qknxgusu.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151476/nr6ixoht5bjkwat0esdm.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151477/ukavhj5fhshlbi1tpxd7.webp"
            },
            {
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151478/mm6dztrt9oo0pg9ao2ig.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151479/zc7zu8ffoa9izkv0ezgg.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151480/eoe2bw0enmomrnisgwt9.webp"
            },
            {
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151481/roexwoln1jjfqbxzwsi3.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151482/ggc9vh1eowhi7itr4icw.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151483/cahnombd553c46f9px1e.webp"
            },
            {
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151484/xw9u4isz4cmfslci4ucb.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151485/wfnitb8pgsoi6pjqu4nl.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151486/mix2cwhaq8911xjffjla.webp"
            },
            {
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151487/gaax9gskloombw027iul.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151488/wwqncflaqovtl0esqnti.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151489/hkqa5bkfvnq1ycpgbebt.webp"
            },
            {
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151490/tcuxvfchkqsq8zzlyhau.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151491/gvaajvxecwi7erxpawqj.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151492/yaw0bnol2wcequaio5vr.webp"
            },
            {
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151493/chyjlku5p4hjvj2m2mf9.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151494/tfbgirai7f487citbdjh.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151495/uerc30wso08cfuiad4zg.webp"
            },
            {
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151496/a6sfmoblluxw3vtytpmy.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151497/njbblav4x9qets8j7z4m.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151498/dx6ye6rubaokmos7fplv.webp"
            },
            {
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v1745151499/nqn11c1hmweryvepeuhk.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v17451514100/oxzop3yzr6unifjlcai3.webp",
                    "https://res.cloudinary.com/dnoq9necr/image/upload/v17451514101/pf4hrziorqf7vkia1vm8.webp"
            }
    };

}
