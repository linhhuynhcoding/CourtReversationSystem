package com.app.CourtReservationSystem.service;

//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.account.AccountUpdatePayload;

import java.util.List;

public interface IAccountService {

    public AccountResponse getAccount(Long id);
    public AccountResponse updateAccount(Long id, AccountUpdatePayload accountUpdatePayload);
    public void deleteAccount(Long id);

    public List<AccountResponse> getAllAccounts();
}
