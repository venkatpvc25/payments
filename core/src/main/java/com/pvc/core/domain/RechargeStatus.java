package com.pvc.core.domain;

public enum RechargeStatus {

    INTIATED("INTIATED"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    PENDING("PENDING"),
    CANCELLED("CANCELLED"),
    REFUNDED("REFUNDED");

    private final String status;

    RechargeStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static RechargeStatus fromString(String status) {
        for (RechargeStatus rs : RechargeStatus.values()) {
            if (rs.status.equalsIgnoreCase(status)) {
                return rs;
            }
        }
        throw new IllegalArgumentException("No enum constant for status: " + status);
    }
}
