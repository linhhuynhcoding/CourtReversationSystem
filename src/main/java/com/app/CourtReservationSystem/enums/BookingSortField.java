package com.app.CourtReservationSystem.enums;

public enum BookingSortField {
    ID("id"),
    NAME("name"),
    DATE("createdDate"),
    PRICE("price"),;

    private final String value;

    BookingSortField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
