package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.security.CustomUserDetails;
import com.app.CourtReservationSystem.service.IStatisticService;
import com.cloudinary.api.exceptions.ApiException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
        name = "statistic"
)
public class StatisticController {
    IStatisticService statisticService;

    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<ApiResponse<?>> getUserNoti(HttpServletRequest request) throws ApiException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Long accountId = ((CustomUserDetails) auth.getPrincipal()).getId();

        var response = statisticService.getSystemStatistic();

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), response));
    }
}
