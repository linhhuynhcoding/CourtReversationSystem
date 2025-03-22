package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.exception.APIException;
import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public AccountResponse getAccount(Integer id) {
        if (accountRepository.existsById(id)) {
            throw new APIException("User not found", HttpStatus.NOT_FOUND);
        }
        
        Optional<Account> account = accountRepository.findById(id);
        
    }
}
