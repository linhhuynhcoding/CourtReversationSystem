package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.auth.LoginPayload;
import com.app.CourtReservationSystem.dto.auth.RegisterPayload;
import com.app.CourtReservationSystem.exception.APIException;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.AccountMapper;
import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.model.Cart;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.repository.RoleRepository;
import com.app.CourtReservationSystem.security.AccountDetailService;
import com.app.CourtReservationSystem.security.CustomUserDetails;
import com.app.CourtReservationSystem.security.JwtTokenProvider;
import com.app.CourtReservationSystem.service.IAuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService implements IAuthService {

    private AccountMapper accountMapper;
    private AccountDetailService accountDetailService;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private AccountRepository accountRepository;
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginPayload loginPayload) {
        if (!accountRepository.existsByUsername(loginPayload.getUsername())) {
            throw new APIException("Username or Password is incorrect!", HttpStatus.UNAUTHORIZED);
        }
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//            loginPayload.getUsername(), loginPayload.getPassword()));

        try {
            CustomUserDetails userDetails = accountDetailService.loadUserByUsername(loginPayload.getUsername());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDetails, loginPayload.getPassword(), userDetails.getAuthorities()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            return token;
        } catch (BadCredentialsException e) {
            throw e;
        }


    }

    @Override
    public String register(RegisterPayload registerPayload) {
        if (accountRepository.existsByUsername(registerPayload.getUsername())) {
            throw new APIException("Username already exist", HttpStatus.BAD_REQUEST);
        }

        if (accountRepository.existsByEmail(registerPayload.getEmail())) {
            throw new APIException("Email already exist", HttpStatus.BAD_REQUEST);
        }

        Account account = accountMapper.toAccount(registerPayload);
        account.setPassword(passwordEncoder.encode(registerPayload.getPassword()));
        account.setAccountRole(roleRepository.findByRole("PLAYER"));
        Cart cart = new Cart();
        cart.setAccount(account);
        account.setCart(cart);


        accountRepository.save(account);
        return "Register successfully!";
    }

    @Override
    public String logout() {
        return "";
    }

    @Override
    public AccountResponse me(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));

        return accountMapper.toDTO(account);
    }


}
