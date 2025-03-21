package com.app.CourtReservationSystem.dbs.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class RoleSeeder implements CommandLineRunner {

//    @Autowired
//    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
//        if (userRepository.count() == 0) {
//            User user1 = new User("John", "Doe");
//            User user2 = new User("John", "Cook");
//            userRepository.save(user1);
//            userRepository.save(user2);
//        }
//        System.out.println(userRepository.count());
    }
}