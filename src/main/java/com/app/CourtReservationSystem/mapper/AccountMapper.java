package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.auth.RegisterPayload;
import com.app.CourtReservationSystem.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel =  "spring")
public interface AccountMapper {
    @Mapping(target = "password", ignore = true)
    Account toAccount(RegisterPayload registerPayload);

    @Mapping(target = "cartId", expression = "java(account.getCart() != null ? account.getCart().getId() : null)")
    AccountResponse toDTO(Account account);

    List<AccountResponse> toDTOs(List<Account> accounts);
}
