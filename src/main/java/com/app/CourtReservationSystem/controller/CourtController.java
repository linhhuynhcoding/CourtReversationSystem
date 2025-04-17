package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.court.CourtFilter;
import com.app.CourtReservationSystem.dto.court.OrgaResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import com.app.CourtReservationSystem.enums.CourtSortField;
import com.app.CourtReservationSystem.service.ICourtService;
import com.cloudinary.api.exceptions.ApiException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.CourtReservationSystem.utils.StringUtil.toOrders;

@RestController
@RequestMapping("/api/v1/courts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
        name = "court"
)
public class CourtController {
    ICourtService courtService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getCourt(
            HttpServletRequest request,
            @PathVariable(name = "id") Long id) {
        OrgaResponse courtResponse = courtService.getCourt(id);

        System.out.println(courtResponse.getName());

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), courtResponse));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> createCourt(
            HttpServletRequest request,
            @Valid @RequestBody CreateCourtPayload createCourtPayload
    ) {
        OrgaResponse courtResponse = courtService.createCourt(createCourtPayload);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), courtResponse));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateCourt(
            HttpServletRequest request,
            @Valid @RequestBody UpdateCourtPayload updateCourtPayload,
            @PathVariable(name = "id") Long id
    ) {
        OrgaResponse courtResponse = courtService.updateCourt(id, updateCourtPayload);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), courtResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteCourt(
            HttpServletRequest request, @PathVariable(name = "id") Long id) {
        courtService.deleteCourt(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Deleted successfully!!", "",
                request.getRequestURI(), null));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> getCourts(HttpServletRequest request, @Valid CourtFilter courtFilter) throws ApiException {
        System.out.println(courtFilter);

        List<Order> orders = toOrders(courtFilter.getSort(), CourtSortField.class);
        Pageable pageable = PageRequest.of(courtFilter.getPage(), courtFilter.getPageSize(), Sort.by(orders));
        Page courtResponses = courtService.getAllCourts(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), courtResponses));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchCourts(HttpServletRequest request, @Valid CourtFilter courtFilter) throws ApiException {
        Page courtResponses = courtService.getAllCourts(courtFilter);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), courtResponses));
    }
}
