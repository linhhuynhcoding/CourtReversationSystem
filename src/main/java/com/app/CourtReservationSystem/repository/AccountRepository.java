package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
    public Account findByUsername(String username);
    Page<Account> findAllByAccountRole(Role accountRole, Pageable pageable);
}
