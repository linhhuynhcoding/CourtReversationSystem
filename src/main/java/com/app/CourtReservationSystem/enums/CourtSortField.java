package com.app.CourtReservationSystem.enums;

public enum CourtSortField {
    ID("id"),
    NAME("name"),
    DATE("createdDate"),
    PRICE("price"),;

    private final String value;

    CourtSortField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
