package com.app.CourtReservationSystem.dbs.seeders;

import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.model.Cart;
import com.app.CourtReservationSystem.model.Role;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.repository.ProductRepository;
import com.app.CourtReservationSystem.repository.RoleRepository;
import com.app.CourtReservationSystem.service.Impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
public class AccountSeeder implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    RedisService redisService;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        try {
//            System.out.println("Clear cache...");
//            redisService.clearCache();
//
//            System.out.println("AccountSeeder started");
//            loadUserData();
//            System.out.println("AccountSeeder done");
//        } catch (Exception e) {
//            System.out.println("AccountSeeder error");
//            e.printStackTrace();
//        }
    }

    @Transactional
    public void loadUserData() {
        Account admin = new Account();
        List<Account> accountList = new ArrayList<>();
        admin.setUsername("admin123");
        admin.setEmail("admin@gmail.com");
        admin.setName("Anh Admin");
        admin.setPassword(passwordEncoder.encode("Admin!123"));
        admin.setAccountRole(roleRepository.findByRole("ADMIN"));

        Cart cart = new Cart();
        cart.setAccount(admin);
        admin.setCart(cart);

        accountList.add(admin);

        Role userRole = roleRepository.findByRole("PLAYER");
        Role managerRole = roleRepository.findByRole("COURT_MANAGER");

        for (int i = 0; i < 100; i++) {
            Account user = new Account();
            user.setUsername("user1234" + i);
            user.setEmail("user" + i + "@gmail.com");
            user.setName("Em User số " + (i + 1));
            user.setPassword(passwordEncoder.encode("User!123"));
            user.setAccountRole(userRole);

            Cart cart2 = new Cart();
            cart2.setAccount(user);
            user.setCart(cart2);

            accountList.add(user);
        }

//        for (int i = 1; i <= 5; i++) {
//            Account account = new Account();
//            account.setUsername("manager" + i);
//            account.setEmail("manager" + i + "@gmail.com");
//            account.setName("Manager " + i);
//            account.setPassword(passwordEncoder.encode("Manager!123"));
//            account.setAccountRole(managerRole);
//
//            accountList.add(account);
//        }

        accountRepository.saveAll(accountList);
    }
}
