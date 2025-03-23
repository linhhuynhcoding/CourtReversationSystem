package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
    public Account findByUsername(String username);
}
