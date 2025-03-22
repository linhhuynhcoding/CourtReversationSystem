package com.app.CourtReservationSystem.service;

//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.CourtReservationSystem.dto.account.AccountResponse;

public interface IAccountService {

    public AccountResponse getAccount(Integer id);
}
