package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Role findByRole(String role);
}
