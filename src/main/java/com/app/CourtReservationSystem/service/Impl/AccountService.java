package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.account.AccountUpdatePayload;
import com.app.CourtReservationSystem.exception.APIException;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.AccountMapper;
import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.service.IAccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService implements IAccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;

    @Override
    public AccountResponse getAccount(Integer id) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

        return accountMapper.toDTO(account);
    }

    @Override
    public AccountResponse updateAccount(Integer id, AccountUpdatePayload accountUpdatePayload) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

        account.setName(accountUpdatePayload.getName());
        account.setEmail(accountUpdatePayload.getEmail());

        Account updatedAccount = accountRepository.save(account);

        return accountMapper.toDTO(updatedAccount);
    }

    @Override
    public void deleteAccount(Integer id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

        accountRepository.delete(account);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();
        List<AccountResponse> accountResponses = accountMapper.toDTOs(accounts);

        return accountResponses;
    }
}
