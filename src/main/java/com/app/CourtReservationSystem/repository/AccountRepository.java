package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Account findByUsername(String username);
}
