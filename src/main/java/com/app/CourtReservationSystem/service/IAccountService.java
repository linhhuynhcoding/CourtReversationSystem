package com.app.CourtReservationSystem.service;

//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.account.AccountUpdatePayload;
import com.app.CourtReservationSystem.dto.account.AddAccountPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {

    public AccountResponse getAccount(Long id);
    public AccountResponse addAccountByRole(AddAccountPayload payload);
    public AccountResponse updateAccount(Long id, AccountUpdatePayload accountUpdatePayload);
    public void deleteAccount(Long id);

    public Page getAllAccounts(Pageable pageable);
    public Page getAllAccountsByRole(String role, Pageable pageable);

}
