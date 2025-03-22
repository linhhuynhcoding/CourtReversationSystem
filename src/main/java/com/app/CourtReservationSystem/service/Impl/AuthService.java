package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.auth.LoginPayload;
import com.app.CourtReservationSystem.dto.auth.RegisterPayload;
import com.app.CourtReservationSystem.exception.APIException;
import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.repository.RoleRepository;
import com.app.CourtReservationSystem.security.JwtTokenProvider;
import com.app.CourtReservationSystem.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    
    @Override
    public String login(LoginPayload loginPayload) {
        if (!accountRepository.existsByUsername(loginPayload.getUsername())){
            throw new APIException("Username or Password is incorrect!", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginPayload.getUsername(), loginPayload.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtTokenProvider.generateToken(authentication);
        
        return token;
    }
    
    @Override
    public String register(RegisterPayload registerPayload) {
        if (accountRepository.existsByUsername(registerPayload.getUsername())) {
            throw new APIException("Username already exist", HttpStatus.BAD_REQUEST);
        }
        
        if (accountRepository.existsByEmail(registerPayload.getEmail())) {
            throw new APIException("Email already exist", HttpStatus.BAD_REQUEST);
        }
        
        Account account = new Account();
        
        account.setEmail(registerPayload.getEmail());
        account.setUsername(registerPayload.getUsername());
        account.setName(registerPayload.getName());
        account.setPassword(passwordEncoder.encode(registerPayload.getPassword()));
        account.setRole(roleRepository.findByRole("PLAYER"));
        accountRepository.save(account);
        
        return "Register successfully!";
    }
    
    @Override
    public String logout() {
        return "";
    }
    
    
}
