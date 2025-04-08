package com.app.CourtReservationSystem.dto.booking;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BookingFilter {

    @Pattern(regexp = "^\\[((\\[[A-Z]{2,8}),(ASC|DESC)\\])(,((\\[[A-Z]{2,8}),(ASC|DESC)\\])){0,4}\\]$")
    private String sort = "[[ID,ASC]]";

    @Min(0)
    @Max(1000)
    private Integer page = 0;

    @Min(10)
    @Max(50)
    private Integer pageSize = 10;

    @Override
    public String toString() {
        return "BookingFilter [sort=" + sort + ", page=" + page + ", pageSize=" + pageSize + "]";
    }
}
