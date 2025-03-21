package com.app.CourtReservationSystem.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAccountService extends UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username);
}
