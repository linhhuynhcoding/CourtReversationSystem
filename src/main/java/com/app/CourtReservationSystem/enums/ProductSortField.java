package com.app.CourtReservationSystem.enums;

public enum ProductSortField {
    ID("id"),
    NAME("name"),
    DATE("createdDate"),
    PRICE("price"),;

    private final String value;

    ProductSortField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
