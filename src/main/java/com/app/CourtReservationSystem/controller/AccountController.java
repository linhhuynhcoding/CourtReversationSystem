package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.account.AccountUpdatePayload;
import com.app.CourtReservationSystem.service.IAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
        name = "CRUD REST APIs for Account Resource"
)
public class AccountController {

    IAccountService accountService;
    
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable(name = "id") Integer id) {
        AccountResponse accountResponse = accountService.getAccount(id);
        
        return new ResponseEntity<>(accountResponse, HttpStatus.FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(
            @Valid @RequestBody AccountUpdatePayload accountUpdatePayload,
            @PathVariable(name = "id") Integer id
    ) {
        AccountResponse accountResponse = accountService.updateAccount(id, accountUpdatePayload);

        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name = "id") Integer id) {
        accountService.deleteAccount(id);

        return new ResponseEntity<>("Deleted successfully!", HttpStatus.NO_CONTENT);
    }


    @GetMapping("/")
    public ResponseEntity<List<AccountResponse>> getAccounts() {
        List<AccountResponse> accountResponses = accountService.getAllAccounts();

        return new ResponseEntity<>(accountResponses, HttpStatus.FOUND);
    }
}
