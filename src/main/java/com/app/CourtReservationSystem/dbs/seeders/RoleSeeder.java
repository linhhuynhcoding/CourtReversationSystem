package com.app.CourtReservationSystem.dbs.seeders;

import com.app.CourtReservationSystem.model.Role;
import com.app.CourtReservationSystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class RoleSeeder implements CommandLineRunner {

  @Autowired
  RoleRepository roleRepository;

  @Override
  public void run(String... args) throws Exception {
    loadUserData();
  }

  private void loadUserData() {
    if (roleRepository.count() == 0) {
      String[] roles = {"USER", "COACH", "COURT_MANAGER", "ADMIN"};
      for (String r : roles) {
        Role role = new Role();
        role.setRole(r);

        roleRepository.save(role);
      }
    }
  }
}