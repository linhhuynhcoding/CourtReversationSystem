package com.app.CourtReservationSystem.dbs.seeders;

import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.repository.ProductRepository;
import com.app.CourtReservationSystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AccountSeeder implements CommandLineRunner {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
    
    private void loadUserData() {
        Account admin = new Account();
        admin.setUsername("admin");
        admin.setEmail("admin@gmail.com");
        admin.setName("Anh Admin");
        admin.setPassword(passwordEncoder.encode("Admin!123"));
        admin.setAccountRole(roleRepository.findByRole("ADMIN"));

        accountRepository.save(admin);
    }

    @Component
    @Order(4)
    public static class CategorySeeder implements CommandLineRunner {

        @Autowired
        ProductRepository productRepository;

        @Override
        public void run(String... args) throws Exception {
            loadProductData();
        }

        private void loadProductData(){
        }
    }
}
