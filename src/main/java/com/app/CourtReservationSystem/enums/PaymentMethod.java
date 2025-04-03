package com.app.CourtReservationSystem.enums;

public enum PaymentMethod {
    COD("COD"),
    VNPAY("VNPAY"),;

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
