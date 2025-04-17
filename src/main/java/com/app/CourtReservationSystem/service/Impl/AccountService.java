package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.account.AccountUpdatePayload;
import com.app.CourtReservationSystem.dto.account.AddAccountPayload;
import com.app.CourtReservationSystem.dto.auth.RegisterPayload;
import com.app.CourtReservationSystem.exception.APIException;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.AccountMapper;
import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.model.Role;
import com.app.CourtReservationSystem.model.relationships.ManagerAccount;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.repository.OrgaRepository;
import com.app.CourtReservationSystem.repository.RoleRepository;
import com.app.CourtReservationSystem.service.IAccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService implements IAccountService {
    private RoleRepository roleRepository;
    AccountMapper accountMapper;
    AccountRepository accountRepository;
    OrgaRepository orgaRepository;

    @Override
    public AccountResponse getAccount(Long id) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

        return accountMapper.toDTO(account);
    }

    @Override
    public AccountResponse updateAccount(Long id, AccountUpdatePayload accountUpdatePayload) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

        account.setName(accountUpdatePayload.getName());
        account.setEmail(accountUpdatePayload.getEmail());

        Account updatedAccount = accountRepository.save(account);

        return accountMapper.toDTO(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

        accountRepository.delete(account);
    }

    @Override
    public Page getAllAccounts(Pageable pageable) {

        Page<Account> accounts = accountRepository.findAll(pageable);

        return accounts.map(accountMapper::toDTO);
    }

    @Override
    public Page getAllAccountsByRole(String role, Pageable pageable) {
        Role roleObj = roleRepository.findByRole(role);
        Page<Account> accounts = accountRepository.findAllByAccountRole(roleObj, pageable);

        return accounts.map(accountMapper::toDTO);
    }

    @Override
    public AccountResponse addAccountByRole(AddAccountPayload payload) {
        if (accountRepository.existsByUsername(payload.getUsername())) {
            throw new APIException("Username already exist", HttpStatus.BAD_REQUEST);
        }

        if (accountRepository.existsByEmail(payload.getEmail())) {
            throw new APIException("Email already exist", HttpStatus.BAD_REQUEST);
        }

        Account account = accountMapper.toAccount(payload);
        account.setAccountRole(roleRepository.findByRole(payload.getRole()));

        if (payload.getRole().equals("COURT_MANAGER")) {
            ManagerAccount managerAccount = new ManagerAccount();
            managerAccount.setAccount(account);
            managerAccount.setCourt(orgaRepository.getReferenceById(payload.getOrgaId()));
            account.setManager(managerAccount);
        }

        accountRepository.save(account);

        return accountMapper.toDTO(account);
    }
}
