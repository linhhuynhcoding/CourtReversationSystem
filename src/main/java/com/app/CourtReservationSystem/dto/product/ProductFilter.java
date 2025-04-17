package com.app.CourtReservationSystem.dto.product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductFilter {

    @Pattern(regexp = "^\\[((\\[[A-Z]{2,8}),(ASC|DESC)\\])(,((\\[[A-Z]{2,8}),(ASC|DESC)\\])){0,4}\\]$")
    private String sort = "[[ID,ASC]]";

    @Min(0)
    @Max(1000)
    private Integer page = 0;

    @Min(10)
    @Max(50)
    private Integer pageSize = 10;

    @NotNull
    private String categoryName;

    private Double priceMin = 0.0;

    private Double priceMax = 5000000.0;

    private String search;

    @Override
    public String toString() {
        return "ProductFilter [sort=" + sort + ", page=" + page + ", pageSize=" + pageSize + "]";
    }
}
