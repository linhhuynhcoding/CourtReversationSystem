package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.account.AccountUpdatePayload;
import com.app.CourtReservationSystem.service.IAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
    name = "Account"
)
public class AccountController {
    
    IAccountService accountService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getAccount(
        HttpServletRequest httpServletRequest,
        @PathVariable(name = "id") Integer id
    ) {
        AccountResponse accountResponse = accountService.getAccount(id);
        
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse<>("Success!", "",
            httpServletRequest.getRequestURI(), accountResponse));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateAccount(
        HttpServletRequest httpServletRequest,
        @Valid @RequestBody AccountUpdatePayload accountUpdatePayload,
        @PathVariable(name = "id") Integer id
    ) {
        AccountResponse accountResponse = accountService.updateAccount(id, accountUpdatePayload);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Success!", "",
            httpServletRequest.getRequestURI(), accountResponse));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteAccount(
        HttpServletRequest httpServletRequest, @PathVariable(name = "id") Integer id) {
        accountService.deleteAccount(id);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Deleted successfully!!", "",
            httpServletRequest.getRequestURI(), null));
    }
    
    
    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> getAccounts(HttpServletRequest httpServletRequest) {
        List<AccountResponse> accountResponses = accountService.getAllAccounts();
        
        
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse<>("Success!", "",
            httpServletRequest.getRequestURI(), accountResponses));
    }
}
