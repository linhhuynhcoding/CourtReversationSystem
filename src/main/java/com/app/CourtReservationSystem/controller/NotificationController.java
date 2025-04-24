package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.court.CourtFilter;
import com.app.CourtReservationSystem.dto.noti.NotiPayload;
import com.app.CourtReservationSystem.enums.CourtSortField;
import com.app.CourtReservationSystem.security.CustomUserDetails;
import com.app.CourtReservationSystem.service.INotificationService;
import com.cloudinary.api.exceptions.ApiException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.CourtReservationSystem.utils.StringUtil.toOrders;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
        name = "notification"
)
public class NotificationController {

    INotificationService notificationService;

    @GetMapping("/user")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<ApiResponse<?>> getUserNoti(HttpServletRequest request) throws ApiException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long accountId = ((CustomUserDetails) auth.getPrincipal()).getId();

        var response = notificationService.getUserNoti(accountId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), response));
    }

    @PostMapping("/")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<ApiResponse<?>> addNoti(HttpServletRequest request, @RequestBody NotiPayload payload) throws ApiException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long accountId = ((CustomUserDetails) auth.getPrincipal()).getId();

        notificationService.addNoti(accountId, payload);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), ""));
    }
}
